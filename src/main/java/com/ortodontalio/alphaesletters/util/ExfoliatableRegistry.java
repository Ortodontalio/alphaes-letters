package com.ortodontalio.alphaesletters.util;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public final class ExfoliatableRegistry {
    private static Supplier<BiMap<Block, Block>> increases;
    private static Supplier<BiMap<Block, Block>> decreases;

    public static void init() {
        increases = Suppliers.memoize(
                () -> ImmutableBiMap.<Block, Block>builder()
                        .put(TechBlocks.LETTER_CONCRETE, TechBlocks.LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.WHITE_LETTER_CONCRETE, TechBlocks.WHITE_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.ORANGE_LETTER_CONCRETE, TechBlocks.ORANGE_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.MAGENTA_LETTER_CONCRETE, TechBlocks.MAGENTA_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.LIGHT_BLUE_LETTER_CONCRETE, TechBlocks.LIGHT_BLUE_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.YELLOW_LETTER_CONCRETE, TechBlocks.YELLOW_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.LIME_LETTER_CONCRETE, TechBlocks.LIME_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.PINK_LETTER_CONCRETE, TechBlocks.PINK_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.GRAY_LETTER_CONCRETE, TechBlocks.GRAY_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.LIGHT_GRAY_LETTER_CONCRETE, TechBlocks.LIGHT_GRAY_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.CYAN_LETTER_CONCRETE, TechBlocks.CYAN_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.PURPLE_LETTER_CONCRETE, TechBlocks.PURPLE_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.BROWN_LETTER_CONCRETE, TechBlocks.BROWN_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.GREEN_LETTER_CONCRETE, TechBlocks.GREEN_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.RED_LETTER_CONCRETE, TechBlocks.RED_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.BLACK_LETTER_CONCRETE, TechBlocks.BLACK_LETTER_EXFOLIATED_CONCRETE)
                        .put(TechBlocks.LETTER_EXFOLIATED_CONCRETE, TechBlocks.BLUE_CONCRETE_WITH_BARS)
                        .put(TechBlocks.WHITE_LETTER_EXFOLIATED_CONCRETE, TechBlocks.CONCRETE_WITH_BARS)
                        .put(TechBlocks.ORANGE_LETTER_EXFOLIATED_CONCRETE, TechBlocks.ORANGE_CONCRETE_WITH_BARS)
                        .put(TechBlocks.MAGENTA_LETTER_EXFOLIATED_CONCRETE, TechBlocks.MAGENTA_CONCRETE_WITH_BARS)
                        .put(TechBlocks.LIGHT_BLUE_LETTER_EXFOLIATED_CONCRETE, TechBlocks.LIGHT_BLUE_CONCRETE_WITH_BARS)
                        .put(TechBlocks.YELLOW_LETTER_EXFOLIATED_CONCRETE, TechBlocks.YELLOW_CONCRETE_WITH_BARS)
                        .put(TechBlocks.LIME_LETTER_EXFOLIATED_CONCRETE, TechBlocks.LIME_CONCRETE_WITH_BARS)
                        .put(TechBlocks.PINK_LETTER_EXFOLIATED_CONCRETE, TechBlocks.PINK_CONCRETE_WITH_BARS)
                        .put(TechBlocks.GRAY_LETTER_EXFOLIATED_CONCRETE, TechBlocks.GRAY_CONCRETE_WITH_BARS)
                        .put(TechBlocks.LIGHT_GRAY_LETTER_EXFOLIATED_CONCRETE, TechBlocks.LIGHT_GRAY_CONCRETE_WITH_BARS)
                        .put(TechBlocks.CYAN_LETTER_EXFOLIATED_CONCRETE, TechBlocks.CYAN_CONCRETE_WITH_BARS)
                        .put(TechBlocks.PURPLE_LETTER_EXFOLIATED_CONCRETE, TechBlocks.PURPLE_CONCRETE_WITH_BARS)
                        .put(TechBlocks.BROWN_LETTER_EXFOLIATED_CONCRETE, TechBlocks.BROWN_CONCRETE_WITH_BARS)
                        .put(TechBlocks.GREEN_LETTER_EXFOLIATED_CONCRETE, TechBlocks.GREEN_CONCRETE_WITH_BARS)
                        .put(TechBlocks.RED_LETTER_EXFOLIATED_CONCRETE, TechBlocks.RED_CONCRETE_WITH_BARS)
                        .put(TechBlocks.BLACK_LETTER_EXFOLIATED_CONCRETE, TechBlocks.BLACK_CONCRETE_WITH_BARS)
                        .build()
        );
        decreases = Suppliers.memoize(() -> increases.get().inverse());
    }

    public static Optional<Block> getIncreasedExfoliationBlock(Block block) {
        return Optional.ofNullable(increases).map(map -> map.get().get(block));
    }

    public static Optional<Block> getDecreasedExfoliatedBlock(Block block) {
        return Optional.ofNullable(decreases).map(map -> map.get().get(block));
    }

    public static Block getUnaffectedExfoliatedBlock(Block block) {
        Block resultBlock = block;

        for (Block nextBlock = decreases.get().get(block);
             nextBlock != null;
             nextBlock = increases.get().get(nextBlock)
        ) {
            resultBlock = nextBlock;
        }

        return resultBlock;
    }

    static Optional<BlockState> getDecreasedExfoliationState(BlockState state) {
        return getDecreasedExfoliatedBlock(state.getBlock()).map(block -> block.getStateWithProperties(state));
    }

    static BlockState getUnaffectedExfoliationState(BlockState state) {
        return getUnaffectedExfoliatedBlock(state.getBlock()).getStateWithProperties(state);
    }
}
