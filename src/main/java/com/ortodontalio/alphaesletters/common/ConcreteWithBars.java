package com.ortodontalio.alphaesletters.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;

public class ConcreteWithBars extends PillarBlock {
    public ConcreteWithBars() {
        super(FabricBlockSettings
                .of(Material.STONE, MapColor.WHITE_GRAY)
                .strength(7.0f, 8.0f)
                .sounds(BlockSoundGroup.STONE));
    }
}
