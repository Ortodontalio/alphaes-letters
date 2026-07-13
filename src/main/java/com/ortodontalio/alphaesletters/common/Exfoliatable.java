package com.ortodontalio.alphaesletters.common;

import com.mojang.serialization.Codec;
import com.ortodontalio.alphaesletters.util.ExfoliatableRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.util.StringIdentifiable;

import java.util.Optional;

public interface Exfoliatable extends Degradable<Exfoliatable.ExfoliatedLevel> {

    @Override
    default Optional<BlockState> getDegradationResult(BlockState state) {
        return ExfoliatableRegistry.getIncreasedExfoliationBlock(state.getBlock())
                .map(block -> block.getStateWithProperties(state));
    }

    @Override
    default float getDegradationChanceMultiplier() {
        return this.getDegradationLevel() == ExfoliatedLevel.UNAFFECTED ? 1.5F : 2;
    }

    enum ExfoliatedLevel implements StringIdentifiable {
        UNAFFECTED("unaffected"),
        EXFOLIATED("exfoliated"),
        TURNED("turned");

        public static final Codec<ExfoliatedLevel> CODEC = StringIdentifiable.createCodec(ExfoliatedLevel::values);
        private final String id;

        private ExfoliatedLevel(final String id) {
            this.id = id;
        }

        @Override
        public String asString() {
            return this.id;
        }
    }
}
