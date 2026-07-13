package com.ortodontalio.alphaesletters.migration;

import com.mojang.serialization.Codec;
import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.common.LetterSpec;
import com.ortodontalio.alphaesletters.letters.MiscLetters;
import com.ortodontalio.alphaesletters.tech.LetterFerroconcrete;
import com.ortodontalio.alphaesletters.tech.StrikethroughBlock;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.impl.attachment.AttachmentTypeImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ortodontalio.alphaesletters.tech.LetterFerroconcrete.LETTER;
import static net.minecraft.block.Block.NOTIFY_ALL;

public final class BlockMigrationHandler {

    private static final int CURRENT_MIGRATION_VERSION = 1;
    private static final Identifier OLD_BLOCK_ID = Identifier.of(AlphaesLetters.MOD_ID, "cropped_letter_concrete");
    private static final Identifier NEW_BLOCK_ID = Identifier.of(AlphaesLetters.MOD_ID, "letter_concrete");
    private static final Map<ServerWorld, Queue<ChunkPos>> worldToProcessQueue = new HashMap<>();
    public static final AttachmentType<Boolean> MIGRATED_ATTACHMENT =
            AttachmentRegistry.createPersistent(
                    Identifier.of(AlphaesLetters.MOD_ID, "chunk_migrated"),
                    Codec.BOOL
            );
    private static final int MAX_BLOCKS_PER_TICK = 50;
    private static final int CHUNK_SIZE = 16;

    private BlockMigrationHandler() {
    }

    public static void register() {
        // 1. NBT migration via mixin – change ID in the palette
        // 2. Chunks processing in the queue
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
            if (world.isClient()) return;
            queueChunkForProcessing(world, chunk.getPos());
        });

        ServerTickEvents.END_SERVER_TICK.register(server ->
                server.getWorlds().forEach(BlockMigrationHandler::processQueuedChunks)
        );

        // 3. Migration Version
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            server.getWorlds().forEach(world -> {
                if (world.isClient()) return;
                BlockMigrationState state = BlockMigrationState.getOrCreate(world);
                if (state.getMigrationVersion() < CURRENT_MIGRATION_VERSION) {
                    AlphaesLetters.LOGGER.info(String.format("World %s migration version updated",
                            world.getRegistryKey().getValue()));
                    state.setMigrationVersion(CURRENT_MIGRATION_VERSION);
                    state.markDirty();
                }
            });
        });
    }

    private static boolean isChunkMigrated(Chunk chunk) {
        return chunk.getAttachedOrElse(MIGRATED_ATTACHMENT, false);
    }

    private static void markChunkMigrated(Chunk chunk) {
        chunk.setAttached(MIGRATED_ATTACHMENT, true);
    }

    /**
     * Palette NBT migration – change old ID to new.
     */
    public static void migrateChunkNbt(NbtCompound chunkNbt) {
        if (chunkNbt == null || !chunkNbt.contains("sections", NbtElement.LIST_TYPE)) {
            return;
        }

        NbtList sections = chunkNbt.getList("sections", NbtElement.COMPOUND_TYPE);
        boolean modified = false;

        for (int i = 0; i < sections.size(); i++) {
            NbtCompound section = sections.getCompound(i);
            NbtCompound blockStates = section.getCompound("block_states");
            if (blockStates == null || blockStates.isEmpty()) continue;

            NbtList palette = blockStates.getList("palette", NbtElement.COMPOUND_TYPE);
            boolean paletteModified = false;

            for (int j = 0; j < palette.size(); j++) {
                NbtCompound entry = palette.getCompound(j);
                if (entry.contains("Name", NbtElement.STRING_TYPE)) {
                    String name = entry.getString("Name");
                    if (OLD_BLOCK_ID.toString().equals(name)) {
                        entry.putString("Name", NEW_BLOCK_ID.toString());
                        paletteModified = true;
                    }
                }
            }

            if (paletteModified) {
                blockStates.put("palette", palette);
                section.put("block_states", blockStates);
                modified = true;
            }
        }

        if (modified) {
            chunkNbt.put("sections", sections);
            AlphaesLetters.LOGGER.info("Migrated NBT palette for chunk");
        }
    }

    private static void queueChunkForProcessing(ServerWorld world, ChunkPos chunkPos) {
        worldToProcessQueue.computeIfAbsent(world, k -> new ArrayDeque<>()).offer(chunkPos);
    }

    /**
     * Check, if the chunk contains letter_concrete with no-null property LETTER.
     */
    private static boolean shouldProcessChunk(Chunk chunk) {
        if (chunk == null) return false;
        if (isChunkMigrated(chunk)) {
            return false;
        }
        for (ChunkSection section : chunk.getSectionArray()) {
            if (section != null && section.hasAny(state ->
                    state.getBlock().equals(TechBlocks.LETTER_CONCRETE)
                            && state.get(LETTER) != null
                            && !Objects.equals(state.get(LETTER), MiscLetters.NONE.asString())
            )) {
                return true;
            }
        }
        return false;
    }

    private static void processQueuedChunks(ServerWorld world) {
        Queue<ChunkPos> queue = worldToProcessQueue.get(world);
        if (queue == null || queue.isEmpty()) return;

        int blocksProcessed = 0;
        while (!queue.isEmpty() && blocksProcessed < MAX_BLOCKS_PER_TICK) {
            ChunkPos chunkPos = queue.poll();
            WorldChunk chunk = world.getChunk(chunkPos.x, chunkPos.z);
            if (shouldProcessChunk(chunk)) {
                blocksProcessed += migrateChunk(world, chunkPos);
                markChunkMigrated(chunk);
            }
        }
    }

    private static int migrateChunk(ServerWorld world, ChunkPos chunkPos) {
        AtomicInteger migrated = new AtomicInteger(0);
        int startX = chunkPos.getStartX();
        int startZ = chunkPos.getStartZ();
        Block targetBlock = TechBlocks.LETTER_CONCRETE;

        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int z = 0; z < CHUNK_SIZE; z++) {
                int blockX = startX + x;
                int blockZ = startZ + z;
                int topY = world.getTopY(Heightmap.Type.MOTION_BLOCKING, blockX, blockZ);

                for (int y = world.getBottomY(); y <= topY; y++) {
                    BlockPos pos = new BlockPos(blockX, y, blockZ);
                    BlockState state = world.getBlockState(pos);

                    if (!state.getBlock().equals(targetBlock)) continue;

                    String letterName = state.get(LETTER);
                    if (letterName == null) continue;

                    Direction facing = state.get(LetterFerroconcrete.FACING);
                    if (facing == null) continue;

                    LetterSpec spec = AlphaesUtils.findLetterByName(letterName);
                    if (spec == MiscLetters.NONE) continue;

                    Block replacementBlock = spec.getBlock();
                    DyeColor color = Optional.ofNullable(state.get(LetterFerroconcrete.COLOR))
                            .orElse(DyeColor.WHITE);

                    processOldBlock(world, replacementBlock, facing, pos, color, migrated, state);
                }
            }
        }
        return migrated.get();
    }

    @SuppressWarnings("java:S3252")
    private static void processOldBlock(ServerWorld world, Block replacementLetterBlock,
                                        Direction direction, BlockPos pos, DyeColor color,
                                        AtomicInteger migrated, BlockState oldState) {
        BlockState newState = replacementLetterBlock.getDefaultState()
                .with(LetterBasic.FACING, direction)
                .with(LetterBasic.COLOR, color);

        // Strikethrough block on +1 (if exists)
        BlockState strikeState = world.getBlockState(pos.offset(direction));
        if (strikeState.isOf(TechBlocks.STRIKETHROUGH_BLOCK)) {
            DyeColor strikeColor = Optional.ofNullable(strikeState.get(StrikethroughBlock.COLOR))
                    .orElse(DyeColor.WHITE);
            newState = newState.with(LetterBasic.HAS_COVER, true)
                    .with(LetterBasic.COVER_COLOR, strikeColor);
        }

        // Letter set to +1
        BlockPos letterPos = pos.offset(direction);
        world.setBlockState(letterPos, newState, NOTIFY_ALL);

        BlockState newBaseState = oldState.with(LETTER, MiscLetters.NONE.asString());
        world.setBlockState(pos, newBaseState, NOTIFY_ALL);

        migrated.getAndIncrement();
    }
}