package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.common.ConcreteWithBars;
import com.ortodontalio.alphaesletters.common.CroppedFerroconcrete;
import com.ortodontalio.alphaesletters.common.DyeingMachine;
import com.ortodontalio.alphaesletters.common.LetterFerroconcrete;
import com.ortodontalio.alphaesletters.common.LetterPowder;
import com.ortodontalio.alphaesletters.common.StrikethroughBlock;
import com.ortodontalio.alphaesletters.util.BlockRegistrator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.WoodType;

public class TechBlocks extends BlockRegistrator {

    public static final Block LETTER_CONCRETE = new LetterFerroconcrete();
    public static final Block CROPPED_LETTER_CONCRETE = new CroppedFerroconcrete();
    public static final Block DYEING_MACHINE = new DyeingMachine();
    public static final Block CONCRETE_WITH_BARS = new ConcreteWithBars();
    public static final Block LETTER_POWDER = new LetterPowder();
    public static final Block STRIKETHROUGH_BLOCK = new StrikethroughBlock();
    public static final Block IRON_FENCE = new FenceBlock(FabricBlockSettings.create()
            .mapColor(MapColor.IRON_GRAY)
            .strength(5.0f, 6.0f)
            .requiresTool());
    public static final Block IRON_FENCE_GATE = new FenceGateBlock(WoodType.OAK, FabricBlockSettings.create()
            .mapColor(MapColor.IRON_GRAY)
            .strength(5.0f, 6.0f)
            .requiresTool());

}
