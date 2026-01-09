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

    public static final Block LETTER_CONCRETE = new LetterFerroconcrete(MapColor.BLUE);
    public static final Block WHITE_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.WHITE);
    public static final Block ORANGE_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.ORANGE);
    public static final Block MAGENTA_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.MAGENTA);
    public static final Block LIGHT_BLUE_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.LIGHT_BLUE);
    public static final Block YELLOW_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.YELLOW);
    public static final Block LIME_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.LIME);
    public static final Block PINK_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.PINK);
    public static final Block GRAY_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.GRAY);
    public static final Block LIGHT_GRAY_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.LIGHT_GRAY);
    public static final Block CYAN_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.CYAN);
    public static final Block PURPLE_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.PURPLE);
    public static final Block BROWN_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.BROWN);
    public static final Block GREEN_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.GREEN);
    public static final Block RED_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.RED);
    public static final Block BLACK_LETTER_CONCRETE = new LetterFerroconcrete(MapColor.BLACK);

    public static final Block CROPPED_LETTER_CONCRETE = new CroppedFerroconcrete();
    public static final Block DYEING_MACHINE = new DyeingMachine();
    public static final Block CONCRETE_WITH_BARS = new ConcreteWithBars();

    public static final Block LETTER_POWDER = new LetterPowder(MapColor.WHITE_GRAY);
    public static final Block ORANGE_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_ORANGE);
    public static final Block MAGENTA_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_MAGENTA);
    public static final Block BLUE_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_BLUE);
    public static final Block LIGHT_BLUE_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_LIGHT_BLUE);
    public static final Block YELLOW_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_YELLOW);
    public static final Block LIME_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_LIME);
    public static final Block PINK_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_PINK);
    public static final Block GRAY_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_GRAY);
    public static final Block LIGHT_GRAY_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_LIGHT_GRAY);
    public static final Block CYAN_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_CYAN);
    public static final Block PURPLE_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_PURPLE);
    public static final Block BROWN_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_BROWN);
    public static final Block GREEN_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_GREEN);
    public static final Block RED_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_RED);
    public static final Block BLACK_LETTER_POWDER = new LetterPowder(MapColor.TERRACOTTA_BLACK);


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
