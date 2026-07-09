package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.Exfoliatable;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.WoodType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.tech.LetterFerroconcrete.LIT;

public class TechBlocks {

    public static final Block CONCRETE_WITH_BARS = registerConcreteWithBars("concrete_with_bars",
            MapColor.WHITE_GRAY);
    public static final Block ORANGE_CONCRETE_WITH_BARS = registerConcreteWithBars("orange_concrete_with_bars",
            MapColor.ORANGE);
    public static final Block MAGENTA_CONCRETE_WITH_BARS = registerConcreteWithBars("magenta_concrete_with_bars",
            MapColor.MAGENTA);
    public static final Block BLUE_CONCRETE_WITH_BARS = registerConcreteWithBars("blue_concrete_with_bars",
            MapColor.BLUE);
    public static final Block LIGHT_BLUE_CONCRETE_WITH_BARS = registerConcreteWithBars("light_blue_concrete_with_bars",
            MapColor.LIGHT_BLUE);
    public static final Block YELLOW_CONCRETE_WITH_BARS = registerConcreteWithBars("yellow_concrete_with_bars",
            MapColor.YELLOW);
    public static final Block LIME_CONCRETE_WITH_BARS = registerConcreteWithBars("lime_concrete_with_bars",
            MapColor.LIME);
    public static final Block PINK_CONCRETE_WITH_BARS = registerConcreteWithBars("pink_concrete_with_bars",
            MapColor.PINK);
    public static final Block GRAY_CONCRETE_WITH_BARS = registerConcreteWithBars("gray_concrete_with_bars",
            MapColor.GRAY);
    public static final Block LIGHT_GRAY_CONCRETE_WITH_BARS = registerConcreteWithBars("light_gray_concrete_with_bars",
            MapColor.LIGHT_GRAY);
    public static final Block CYAN_CONCRETE_WITH_BARS = registerConcreteWithBars("cyan_concrete_with_bars",
            MapColor.CYAN);
    public static final Block PURPLE_CONCRETE_WITH_BARS = registerConcreteWithBars("purple_concrete_with_bars",
            MapColor.PURPLE);
    public static final Block BROWN_CONCRETE_WITH_BARS = registerConcreteWithBars("brown_concrete_with_bars",
            MapColor.BROWN);
    public static final Block GREEN_CONCRETE_WITH_BARS = registerConcreteWithBars("green_concrete_with_bars",
            MapColor.GREEN);
    public static final Block RED_CONCRETE_WITH_BARS = registerConcreteWithBars("red_concrete_with_bars",
            MapColor.RED);
    public static final Block BLACK_CONCRETE_WITH_BARS = registerConcreteWithBars("black_concrete_with_bars",
            MapColor.BLACK);

    public static final Block LETTER_POWDER = registerLetterPowder("letter_powder",
            MapColor.WHITE_GRAY,
            CONCRETE_WITH_BARS,
            Blocks.WHITE_CONCRETE_POWDER);
    public static final Block ORANGE_LETTER_POWDER = registerLetterPowder("orange_letter_powder",
            MapColor.TERRACOTTA_ORANGE,
            ORANGE_CONCRETE_WITH_BARS,
            Blocks.ORANGE_CONCRETE_POWDER);
    public static final Block MAGENTA_LETTER_POWDER = registerLetterPowder("magenta_letter_powder",
            MapColor.TERRACOTTA_MAGENTA,
            MAGENTA_CONCRETE_WITH_BARS,
            Blocks.MAGENTA_CONCRETE_POWDER);
    public static final Block BLUE_LETTER_POWDER = registerLetterPowder("blue_letter_powder",
            MapColor.TERRACOTTA_BLUE,
            BLUE_CONCRETE_WITH_BARS,
            Blocks.BLUE_CONCRETE_POWDER);
    public static final Block LIGHT_BLUE_LETTER_POWDER = registerLetterPowder("light_blue_letter_powder",
            MapColor.TERRACOTTA_LIGHT_BLUE,
            LIGHT_BLUE_CONCRETE_WITH_BARS,
            Blocks.LIGHT_BLUE_CONCRETE_POWDER);
    public static final Block YELLOW_LETTER_POWDER = registerLetterPowder("yellow_letter_powder",
            MapColor.TERRACOTTA_YELLOW,
            YELLOW_CONCRETE_WITH_BARS,
            Blocks.YELLOW_CONCRETE_POWDER);
    public static final Block LIME_LETTER_POWDER = registerLetterPowder("lime_letter_powder",
            MapColor.TERRACOTTA_LIME,
            LIME_CONCRETE_WITH_BARS,
            Blocks.LIME_CONCRETE_POWDER);
    public static final Block PINK_LETTER_POWDER = registerLetterPowder("pink_letter_powder",
            MapColor.TERRACOTTA_PINK,
            PINK_CONCRETE_WITH_BARS,
            Blocks.PINK_CONCRETE_POWDER);
    public static final Block GRAY_LETTER_POWDER = registerLetterPowder("gray_letter_powder",
            MapColor.TERRACOTTA_GRAY,
            GRAY_CONCRETE_WITH_BARS,
            Blocks.GRAY_CONCRETE_POWDER);
    public static final Block LIGHT_GRAY_LETTER_POWDER = registerLetterPowder("light_gray_letter_powder",
            MapColor.TERRACOTTA_LIGHT_GRAY,
            LIGHT_GRAY_CONCRETE_WITH_BARS,
            Blocks.LIGHT_GRAY_CONCRETE_POWDER);
    public static final Block CYAN_LETTER_POWDER = registerLetterPowder("cyan_letter_powder",
            MapColor.TERRACOTTA_CYAN,
            CYAN_CONCRETE_WITH_BARS,
            Blocks.CYAN_CONCRETE_POWDER);
    public static final Block PURPLE_LETTER_POWDER = registerLetterPowder("purple_letter_powder",
            MapColor.TERRACOTTA_PURPLE,
            PURPLE_CONCRETE_WITH_BARS,
            Blocks.PURPLE_CONCRETE_POWDER);
    public static final Block BROWN_LETTER_POWDER = registerLetterPowder("brown_letter_powder",
            MapColor.TERRACOTTA_BROWN,
            BROWN_CONCRETE_WITH_BARS,
            Blocks.BROWN_CONCRETE_POWDER);
    public static final Block GREEN_LETTER_POWDER = registerLetterPowder("green_letter_powder",
            MapColor.TERRACOTTA_GREEN,
            GREEN_CONCRETE_WITH_BARS,
            Blocks.GREEN_CONCRETE_POWDER);
    public static final Block RED_LETTER_POWDER = registerLetterPowder("red_letter_powder",
            MapColor.TERRACOTTA_RED,
            RED_CONCRETE_WITH_BARS,
            Blocks.RED_CONCRETE_POWDER);
    public static final Block BLACK_LETTER_POWDER = registerLetterPowder("black_letter_powder",
            MapColor.TERRACOTTA_BLACK,
            BLACK_CONCRETE_WITH_BARS,
            Blocks.BLACK_CONCRETE_POWDER);

    public static final Block LETTER_CONCRETE = getFullyPaintedFerroconcrete("letter_concrete", MapColor.BLUE);
    public static final Block WHITE_LETTER_CONCRETE = getFullyPaintedFerroconcrete("white_letter_concrete",
            MapColor.WHITE);
    public static final Block ORANGE_LETTER_CONCRETE = getFullyPaintedFerroconcrete("orange_letter_concrete",
            MapColor.ORANGE);
    public static final Block MAGENTA_LETTER_CONCRETE = getFullyPaintedFerroconcrete("magenta_letter_concrete",
            MapColor.MAGENTA);
    public static final Block LIGHT_BLUE_LETTER_CONCRETE = getFullyPaintedFerroconcrete("light_blue_letter_concrete",
            MapColor.LIGHT_BLUE);
    public static final Block YELLOW_LETTER_CONCRETE = getFullyPaintedFerroconcrete("yellow_letter_concrete",
            MapColor.YELLOW);
    public static final Block LIME_LETTER_CONCRETE = getFullyPaintedFerroconcrete("lime_letter_concrete",
            MapColor.LIME);
    public static final Block PINK_LETTER_CONCRETE = getFullyPaintedFerroconcrete("pink_letter_concrete",
            MapColor.PINK);
    public static final Block GRAY_LETTER_CONCRETE = getFullyPaintedFerroconcrete("gray_letter_concrete",
            MapColor.GRAY);
    public static final Block LIGHT_GRAY_LETTER_CONCRETE = getFullyPaintedFerroconcrete("light_gray_letter_concrete",
            MapColor.LIGHT_GRAY);
    public static final Block CYAN_LETTER_CONCRETE = getFullyPaintedFerroconcrete("cyan_letter_concrete",
            MapColor.CYAN);
    public static final Block PURPLE_LETTER_CONCRETE = getFullyPaintedFerroconcrete("purple_letter_concrete",
            MapColor.PURPLE);
    public static final Block BROWN_LETTER_CONCRETE = getFullyPaintedFerroconcrete("brown_letter_concrete",
            MapColor.BROWN);
    public static final Block GREEN_LETTER_CONCRETE = getFullyPaintedFerroconcrete("green_letter_concrete",
            MapColor.GREEN);
    public static final Block RED_LETTER_CONCRETE = getFullyPaintedFerroconcrete("red_letter_concrete",
            MapColor.RED);
    public static final Block BLACK_LETTER_CONCRETE = getFullyPaintedFerroconcrete("black_letter_concrete",
            MapColor.BLACK);

    public static final Block LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete("letter_exfoliated_concrete",
            MapColor.BLUE);
    public static final Block WHITE_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "white_letter_exfoliated_concrete", MapColor.WHITE);
    public static final Block ORANGE_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "orange_letter_exfoliated_concrete", MapColor.ORANGE);
    public static final Block MAGENTA_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "magenta_letter_exfoliated_concrete", MapColor.MAGENTA);
    public static final Block LIGHT_BLUE_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "light_blue_letter_exfoliated_concrete", MapColor.LIGHT_BLUE);
    public static final Block YELLOW_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "yellow_letter_exfoliated_concrete", MapColor.YELLOW);
    public static final Block LIME_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "lime_letter_exfoliated_concrete", MapColor.LIME);
    public static final Block PINK_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "pink_letter_exfoliated_concrete", MapColor.PINK);
    public static final Block GRAY_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "gray_letter_exfoliated_concrete", MapColor.GRAY);
    public static final Block LIGHT_GRAY_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "light_gray_letter_exfoliated_concrete", MapColor.LIGHT_GRAY);
    public static final Block CYAN_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "cyan_letter_exfoliated_concrete", MapColor.CYAN);
    public static final Block PURPLE_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "purple_letter_exfoliated_concrete", MapColor.PURPLE);
    public static final Block BROWN_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "brown_letter_exfoliated_concrete", MapColor.BROWN);
    public static final Block GREEN_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "green_letter_exfoliated_concrete", MapColor.GREEN);
    public static final Block RED_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "red_letter_exfoliated_concrete", MapColor.RED);
    public static final Block BLACK_LETTER_EXFOLIATED_CONCRETE = getExfoliatedFerroconcrete(
            "black_letter_exfoliated_concrete", MapColor.BLACK);

    //public static final Block CROPPED_LETTER_CONCRETE = new CroppedFerroconcrete();
    public static final Block DYEING_MACHINE = registerDyeingMachine();

    public static final Block STRIKETHROUGH_BLOCK = registerStrikethroughBlock();
    public static final Block IRON_FENCE = registerIronFenceBlock();
    public static final Block IRON_FENCE_GATE = registerIronFenceGate();

    private static Block getFullyPaintedFerroconcrete(String id, MapColor mapColor) {
        return registerFerroconcrete(id, Exfoliatable.ExfoliatedLevel.UNAFFECTED, mapColor);
    }

    private static Block getExfoliatedFerroconcrete(String id, MapColor mapColor) {
        return registerFerroconcrete(id, Exfoliatable.ExfoliatedLevel.EXFOLIATED, mapColor);
    }

    private static Block registerFerroconcrete(String id, Exfoliatable.ExfoliatedLevel level, MapColor mapColor) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, id)),
                settings -> new LetterFerroconcrete(level, settings), AbstractBlock.Settings
                        .create()
                        .mapColor(mapColor)
                        .strength(5.0f, 10.0f)
                        .sounds(BlockSoundGroup.STONE)
                        .luminance(state -> Boolean.TRUE.equals(state.get(LIT)) ? 10 : 0)
                        .ticksRandomly()
                        .requiresTool());
    }

    private static Block registerDyeingMachine() {
        return Registry.register(Registries.BLOCK, RegistryKey.of(RegistryKeys.BLOCK,
                Identifier.of(AlphaesLetters.MOD_ID, DyeingMachine.ID)), new DyeingMachine());
    }

    private static Block registerConcreteWithBars(String id, MapColor mapColor) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, id)),
                ConcreteWithBars::new, AbstractBlock.Settings
                        .create()
                        .mapColor(mapColor)
                        .strength(7.0f, 8.0f)
                        .sounds(BlockSoundGroup.STONE)
                        .requiresTool());
    }

    private static Block registerLetterPowder(String id, MapColor mapColor, Block relatedConcrete,
                                              Block relatedVanillaPowder) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, id)),
                settings -> new LetterPowder(settings, relatedConcrete, relatedVanillaPowder),
                AbstractBlock.Settings
                        .create()
                        .mapColor(mapColor)
                        .strength(2.0f, 1.0f)
                        .sounds(BlockSoundGroup.SAND));
    }

    private static Block registerStrikethroughBlock() {
        return Registry.register(Registries.BLOCK, Identifier.of(AlphaesLetters.MOD_ID,
                "strikethrough_block"), new StrikethroughBlock());
    }

    private static Block registerIronFenceBlock() {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID,
                        "iron_fence")),
                FenceBlock::new, AbstractBlock.Settings.create()
                        .mapColor(MapColor.IRON_GRAY)
                        .sounds(BlockSoundGroup.METAL)
                        .strength(5.0f, 6.0f)
                        .requiresTool());
    }

    private static Block registerIronFenceGate() {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID,
                        "iron_fence_gate")),
                settings -> new FenceGateBlock(WoodType.WARPED, settings), AbstractBlock.Settings.create()
                        .mapColor(MapColor.IRON_GRAY)
                        .strength(5.0f, 6.0f)
                        .sounds(BlockSoundGroup.METAL)
                        .requiresTool());
    }

    public static void registerAll() {
        // Empty, since all blocks have been registered in static way.
    }
}
