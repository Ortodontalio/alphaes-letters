package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.util.BlockRegistrator;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.WoodType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class TechBlocks extends BlockRegistrator {

    public static final Block LETTER_CONCRETE = new LetterFerroconcrete();
    public static final Block CROPPED_LETTER_CONCRETE = new CroppedFerroconcrete();
    public static final Block DYEING_MACHINE = new DyeingMachine();
    public static final Block CONCRETE_WITH_BARS = new ConcreteWithBars();
    public static final Block LETTER_POWDER = new LetterPowder();
    public static final Block STRIKETHROUGH_BLOCK = new StrikethroughBlock();
    public static final Block IRON_FENCE = new FenceBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.IRON_GRAY)
            .strength(5.0f, 6.0f)
            .requiresTool()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, "iron_fence"))));
    public static final Block IRON_FENCE_GATE = new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.create()
            .mapColor(MapColor.IRON_GRAY)
            .strength(5.0f, 6.0f)
            .requiresTool()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, "iron_fence_gate"))));
}
