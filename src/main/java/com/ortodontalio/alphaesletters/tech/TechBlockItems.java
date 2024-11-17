package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.util.BlockItemRegistrator;
import com.ortodontalio.alphaesletters.util.GroupRegistrable;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class TechBlockItems extends BlockItemRegistrator implements GroupRegistrable {

    public static final BlockItem LETTER_CONCRETE = new BlockItem(TechBlocks.LETTER_CONCRETE, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "letter_concrete"))));
    public static final BlockItem CROPPED_LETTER_CONCRETE = new BlockItem(TechBlocks.CROPPED_LETTER_CONCRETE, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "cropped_letter_concrete"))));
    public static final BlockItem DYEING_MACHINE = new BlockItem(TechBlocks.DYEING_MACHINE, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "dyeing_machine"))));
    public static final BlockItem LETTER_POWDER = new BlockItem(TechBlocks.LETTER_POWDER, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "letter_powder"))));
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
    public static final BlockItem SYMBOL_WALL = new BlockItem(TechBlocks.SYMBOL_WALL, new Item
            .Settings()
            .useBlockPrefixedTranslationKey()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "symbol_wall"))));

}
