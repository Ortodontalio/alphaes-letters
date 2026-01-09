package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.util.BlockItemRegistrator;
import com.ortodontalio.alphaesletters.util.GroupRegistrable;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class TechBlockItems extends BlockItemRegistrator implements GroupRegistrable {

    public static final BlockItem LETTER_CONCRETE = getBlockItem(TechBlocks.LETTER_CONCRETE,
            "letter_concrete");
    public static final BlockItem WHITE_LETTER_CONCRETE = getBlockItem(TechBlocks.WHITE_LETTER_CONCRETE,
            "white_letter_concrete");
    public static final BlockItem ORANGE_LETTER_CONCRETE = getBlockItem(TechBlocks.ORANGE_LETTER_CONCRETE,
            "orange_letter_concrete");
    public static final BlockItem MAGENTA_LETTER_CONCRETE = getBlockItem(TechBlocks.MAGENTA_LETTER_CONCRETE,
            "magenta_letter_concrete");
    public static final BlockItem LIGHT_BLUE_LETTER_CONCRETE = getBlockItem(TechBlocks.LIGHT_BLUE_LETTER_CONCRETE,
            "light_blue_letter_concrete");
    public static final BlockItem YELLOW_LETTER_CONCRETE = getBlockItem(TechBlocks.YELLOW_LETTER_CONCRETE,
            "yellow_letter_concrete");
    public static final BlockItem LIME_LETTER_CONCRETE = getBlockItem(TechBlocks.LIME_LETTER_CONCRETE,
            "lime_letter_concrete");
    public static final BlockItem PINK_LETTER_CONCRETE = getBlockItem(TechBlocks.PINK_LETTER_CONCRETE,
            "pink_letter_concrete");
    public static final BlockItem GRAY_LETTER_CONCRETE = getBlockItem(TechBlocks.GRAY_LETTER_CONCRETE,
            "gray_letter_concrete");
    public static final BlockItem LIGHT_GRAY_LETTER_CONCRETE = getBlockItem(TechBlocks.LIGHT_GRAY_LETTER_CONCRETE,
            "light_gray_letter_concrete");
    public static final BlockItem CYAN_LETTER_CONCRETE = getBlockItem(TechBlocks.CYAN_LETTER_CONCRETE,
            "cyan_letter_concrete");
    public static final BlockItem PURPLE_LETTER_CONCRETE = getBlockItem(TechBlocks.PURPLE_LETTER_CONCRETE,
            "purple_letter_concrete");
    public static final BlockItem BROWN_LETTER_CONCRETE = getBlockItem(TechBlocks.BROWN_LETTER_CONCRETE,
            "brown_letter_concrete");
    public static final BlockItem GREEN_LETTER_CONCRETE = getBlockItem(TechBlocks.GREEN_LETTER_CONCRETE,
            "green_letter_concrete");
    public static final BlockItem RED_LETTER_CONCRETE = getBlockItem(TechBlocks.RED_LETTER_CONCRETE,
            "red_letter_concrete");
    public static final BlockItem BLACK_LETTER_CONCRETE = getBlockItem(TechBlocks.BLACK_LETTER_CONCRETE,
            "black_letter_concrete");

    public static final BlockItem CROPPED_LETTER_CONCRETE = new BlockItem(TechBlocks.CROPPED_LETTER_CONCRETE, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "cropped_letter_concrete"))));
    public static final BlockItem DYEING_MACHINE = new BlockItem(TechBlocks.DYEING_MACHINE, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "dyeing_machine"))));

    public static final BlockItem LETTER_POWDER = getBlockItem(TechBlocks.LETTER_POWDER, "letter_powder");
    public static final BlockItem BLUE_LETTER_POWDER = getBlockItem(TechBlocks.BLUE_LETTER_POWDER,
            "blue_letter_powder");
    public static final BlockItem ORANGE_LETTER_POWDER = getBlockItem(TechBlocks.ORANGE_LETTER_POWDER,
            "orange_letter_powder");
    public static final BlockItem MAGENTA_LETTER_POWDER = getBlockItem(TechBlocks.MAGENTA_LETTER_POWDER,
            "magenta_letter_powder");
    public static final BlockItem LIGHT_BLUE_LETTER_POWDER = getBlockItem(TechBlocks.LIGHT_BLUE_LETTER_POWDER,
            "light_blue_letter_powder");
    public static final BlockItem YELLOW_LETTER_POWDER = getBlockItem(TechBlocks.YELLOW_LETTER_POWDER,
            "yellow_letter_powder");
    public static final BlockItem LIME_LETTER_POWDER = getBlockItem(TechBlocks.LIME_LETTER_POWDER,
            "lime_letter_powder");
    public static final BlockItem PINK_LETTER_POWDER = getBlockItem(TechBlocks.PINK_LETTER_POWDER,
            "pink_letter_powder");
    public static final BlockItem GRAY_LETTER_POWDER = getBlockItem(TechBlocks.GRAY_LETTER_POWDER,
            "gray_letter_powder");
    public static final BlockItem LIGHT_GRAY_LETTER_POWDER = getBlockItem(TechBlocks.LIGHT_GRAY_LETTER_POWDER,
            "light_gray_letter_powder");
    public static final BlockItem CYAN_LETTER_POWDER = getBlockItem(TechBlocks.CYAN_LETTER_POWDER,
            "cyan_letter_powder");
    public static final BlockItem PURPLE_LETTER_POWDER = getBlockItem(TechBlocks.PURPLE_LETTER_POWDER,
            "purple_letter_powder");
    public static final BlockItem BROWN_LETTER_POWDER = getBlockItem(TechBlocks.BROWN_LETTER_POWDER,
            "brown_letter_powder");
    public static final BlockItem GREEN_LETTER_POWDER = getBlockItem(TechBlocks.GREEN_LETTER_POWDER,
            "green_letter_powder");
    public static final BlockItem RED_LETTER_POWDER = getBlockItem(TechBlocks.RED_LETTER_POWDER,
            "red_letter_powder");
    public static final BlockItem BLACK_LETTER_POWDER = getBlockItem(TechBlocks.BLACK_LETTER_POWDER,
            "black_letter_powder");

    public static final BlockItem CONCRETE_WITH_BARS = new BlockItem(TechBlocks.CONCRETE_WITH_BARS, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "concrete_with_bars"))));
    public static final BlockItem STRIKETHROUGH_BLOCK = new BlockItem(TechBlocks.STRIKETHROUGH_BLOCK, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "strikethrough_block"))));
    public static final BlockItem IRON_FENCE = new BlockItem(TechBlocks.IRON_FENCE, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "iron_fence"))));
    public static final BlockItem IRON_FENCE_GATE = new BlockItem(TechBlocks.IRON_FENCE_GATE, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "iron_fence_gate"))));

    private static BlockItem getBlockItem(Block concrete, String path) {
        return new BlockItem(concrete, new Item
                .Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, path))));
    }
}
