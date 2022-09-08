package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.annotations.BlockRegistrator;
import com.ortodontalio.alphaesletters.common.DyeingMachine;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.common.LetterPowder;
import com.ortodontalio.alphaesletters.common.StrikethroughBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;

@BlockRegistrator
public class TechBlocks {

    public static final Block LETTER_CONCRETE = new LetterBasic();
    public static final Block DYEING_MACHINE = new DyeingMachine();
    public static final Block LETTER_POWDER = new LetterPowder();
    public static final Block STRIKETHROUGH_BLOCK = new StrikethroughBlock();
    public static final Block IRON_FENCE = new FenceBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY)
            .strength(5.0f,6.0f)
            .requiresTool());
    public static final Block IRON_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY)
            .strength(5.0f,6.0f)
            .requiresTool());

}
