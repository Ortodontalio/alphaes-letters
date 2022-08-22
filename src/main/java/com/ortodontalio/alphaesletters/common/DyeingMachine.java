package com.ortodontalio.alphaesletters.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class DyeingMachine extends Block {

    public DyeingMachine() {
        super(FabricBlockSettings
                .of(Material.METAL)
                .strength(4.0f, 6.0f)
                .sounds(BlockSoundGroup.METAL)
                .requiresTool());
    }

}
