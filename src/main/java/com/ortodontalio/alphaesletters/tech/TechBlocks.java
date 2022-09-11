package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.common.*;
import com.ortodontalio.alphaesletters.util.BlockRegistrator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class TechBlocks extends BlockRegistrator {

    public static final Block LETTER_CONCRETE = new Block(FabricBlockSettings
                                                            .of(Material.STONE, MapColor.BLUE)
                                                            .strength(5.0f, 10.0f)
                                                            .sounds(BlockSoundGroup.STONE)
                                                            .requiresTool());
    public static final Block CROPPED_LETTER_CONCRETE = new LetterBasic();
    public static final Block DYEING_MACHINE = new DyeingMachine();
    public static final Block LETTER_POWDER = new LetterPowder();
    public static final Block STRIKETHROUGH_BLOCK = new StrikethroughBlock();
    public static final Block IRON_FENCE = new FenceBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY)
            .strength(5.0f,6.0f)
            .requiresTool());
    public static final Block IRON_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY)
            .strength(5.0f,6.0f)
            .requiresTool());

    public static void registerBlocks() {
        registerBlocks(TechBlocks.class);
    }
}
