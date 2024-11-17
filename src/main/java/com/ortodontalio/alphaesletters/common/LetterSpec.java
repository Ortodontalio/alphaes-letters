package com.ortodontalio.alphaesletters.common;

import net.minecraft.block.Block;
import net.minecraft.util.StringIdentifiable;

import java.util.Locale;

public interface LetterSpec extends StringIdentifiable {
    String name();

    Block getBlock();

    @Override
    default String asString() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
