package com.ortodontalio.alphaesletters.common;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;

public interface HasColor {
    EnumProperty<DyeColor> COLOR = EnumProperty.of("color", DyeColor.class, DyeColor.values());

    default int getColor(BlockState state) {
        return state.get(COLOR).getMapColor().color;
    }
}
