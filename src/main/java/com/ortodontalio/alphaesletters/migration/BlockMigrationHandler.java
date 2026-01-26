package com.ortodontalio.alphaesletters.migration;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.common.LetterSpec;
import com.ortodontalio.alphaesletters.tech.LetterFerroconcrete;
import com.ortodontalio.alphaesletters.tech.StrikethroughBlock;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
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
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ortodontalio.alphaesletters.tech.LetterFerroconcrete.LETTER;
import static net.minecraft.block.Block.NOTIFY_ALL;
import static net.minecraft.nbt.NbtElement.*;

//TODO Migrations
public class BlockMigrationHandler {

    private static final int CURRENT_MIGRATION_VERSION = 1;
    private static final Identifier OLD_BLOCK_ID = Identifier.of(AlphaesLetters.MOD_ID,
            "cropped_letter_concrete");
    private static final Identifier REPLACEMENT_CONCRETE_ID = Identifier.of(AlphaesLetters.MOD_ID,
            "letter_concrete");
    private static final Map<ServerWorld, Queue<ChunkPos>> worldToProcessQueue = new HashMap<>();
    private static final int MAX_BLOCKS_PER_TICK = 50;
    private static final String BLOCK_STATES_KEY = "block_states";
    private static final String PALETTE_KEY = "palette";
    private static final String NAME_KEY = "Name";
    private static final String SECTIONS_KEY = "sections";
    private static final int CHUNK_SIZE = 16;

    private BlockMigrationHandler() {
    }

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            server.getWorlds().forEach(world -> {
                if (!world.isClient()) {
                    System.out.println(">>>>>>>>>>>>LOADED WORLD: " + server.getSaveProperties().getLevelName());
                    System.out.println(">>>>>>>>>>>>LOADED WORLD: " + world.getChunkManager().getLoadedChunkCount());
                }
            });

//            BlockMigrationState state = BlockMigrationState.getOrCreate(world);
//            if (state.getMigrationVersion() < CURRENT_MIGRATION_VERSION) {
//                state.setMigrationVersion(CURRENT_MIGRATION_VERSION);
//                state.markDirty();
//            } else {
//
//            }
        });
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
            if (!world.isClient()) {
                queueChunkForProcessing(world, chunk.getPos());
            }
        });

        // Тикер для обработки очереди
        ServerTickEvents.END_SERVER_TICK.register(server ->
                server.getWorlds().forEach(BlockMigrationHandler::processQueuedChunks));
    }

    private static void queueChunkForProcessing(ServerWorld world, ChunkPos chunkPos) {
        worldToProcessQueue
                .computeIfAbsent(world, k -> new ArrayDeque<>())
                .offer(chunkPos);
    }

    private static boolean shouldProcessChunk(Chunk chunk) {
        if (chunk == null) {
            return false;
        }
        ChunkSection[] sections = chunk.getSectionArray();

        for (ChunkSection section : sections) {
            if (section != null && section.hasAny(state ->
                    state.getBlock().equals(TechBlocks.LETTER_CONCRETE))) {
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
                blocksProcessed += migrateChunk(world, chunk.getPos());
            }
        }
    }

    public static void migrateChunkNbt(NbtCompound chunkNbt) {
        if (chunkNbt == null || !chunkNbt.contains(SECTIONS_KEY, LIST_TYPE)) {
            return;
        }

        NbtList sections = chunkNbt.getList(SECTIONS_KEY, COMPOUND_TYPE);
        for (int i = 0; i < sections.size(); i++) {
            NbtCompound section = sections.getCompound(i);
            migrateSectionNbt(section);
        }
    }

    private static void migrateSectionNbt(NbtCompound section) {
        if (!section.contains(BLOCK_STATES_KEY, COMPOUND_TYPE)) {
            return;
        }

        NbtCompound blockStates = section.getCompound(BLOCK_STATES_KEY);
        if (!blockStates.contains(PALETTE_KEY, LIST_TYPE)) {
            return;
        }

        NbtList palette = blockStates.getList(PALETTE_KEY, COMPOUND_TYPE);
        boolean paletteModified = false;

        for (int i = 0; i < palette.size(); i++) {
            NbtCompound blockEntry = palette.getCompound(i);
            if (blockEntry.contains(NAME_KEY, STRING_TYPE)) {
                String blockName = blockEntry.getString(NAME_KEY);
                if (blockName.equals(OLD_BLOCK_ID.toString())) {
                    blockEntry.putString(NAME_KEY, REPLACEMENT_CONCRETE_ID.toString());
                    paletteModified = true;
                }
            }
        }

        if (paletteModified) {
            blockStates.put(PALETTE_KEY, palette);
            section.put(BLOCK_STATES_KEY, blockStates);
        }
    }

    private static int migrateChunk(ServerWorld world, ChunkPos chunk) {
        AtomicInteger migrated = new AtomicInteger();
        int startX = chunk.getStartX();
        int startZ = chunk.getStartZ();

        for (int x = startX; x < startX + CHUNK_SIZE; x++) {
            for (int z = startZ; z < startZ + CHUNK_SIZE; z++) {
                int topY = world.getTopY(Heightmap.Type.MOTION_BLOCKING, x, z);
                for (int y = world.getBottomY(); y <= topY; y++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    var blockState = world.getBlockState(pos);
                    if (Registries.BLOCK.getId(blockState.getBlock()).equals(REPLACEMENT_CONCRETE_ID)) {
                        Optional.ofNullable(AlphaesUtils.findLetterByName(blockState.getNullable(LETTER)))
                                .map(LetterSpec::getBlock)
                                .ifPresent(replacementLetterBlock -> Optional.ofNullable(blockState
                                                .getNullable(LetterFerroconcrete.FACING))
                                        .ifPresent(direction -> processOldBlock(world, replacementLetterBlock,
                                                direction, pos, blockState, migrated)));
                    }
                }
            }
        }
        return migrated.get();
    }

    @SuppressWarnings("java:S3252")
    private static void processOldBlock(ServerWorld world, Block replacementLetterBlock, Direction direction,
                                        BlockPos pos, BlockState blockState, AtomicInteger migrated) {
        var nearBlockState = world.getBlockState(pos.offset(direction));
        if (nearBlockState.isOf(TechBlocks.STRIKETHROUGH_BLOCK)) {
            world.setBlockState(pos.offset(direction, 2),
                    TechBlocks.STRIKETHROUGH_BLOCK.getDefaultState()
                            .with(StrikethroughBlock.FACING, direction)
                            .with(StrikethroughBlock.COLOR, Optional.ofNullable(nearBlockState
                                            .getNullable(StrikethroughBlock.COLOR))
                                    .orElse(DyeColor.RED)),
                    NOTIFY_ALL);
        }
        world.setBlockState(pos.offset(direction),
                replacementLetterBlock.getDefaultState()
                        .with(LetterBasic.FACING, direction)
                        .with(LetterBasic.COLOR, Optional.ofNullable(blockState.getNullable(LetterFerroconcrete.COLOR))
                                .orElse(DyeColor.WHITE)),
                NOTIFY_ALL);
        migrated.getAndIncrement();
    }
}

