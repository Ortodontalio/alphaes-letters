package com.ortodontalio.alphaesletters.numbers;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NumbersBlockItems {

    public static final BlockItem LETTER_1 = new BlockItem(NumbersBlocks.LETTER_1, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_2 = new BlockItem(NumbersBlocks.LETTER_2, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_3 = new BlockItem(NumbersBlocks.LETTER_3, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_4 = new BlockItem(NumbersBlocks.LETTER_4, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_5 = new BlockItem(NumbersBlocks.LETTER_5, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_6 = new BlockItem(NumbersBlocks.LETTER_6, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_7 = new BlockItem(NumbersBlocks.LETTER_7, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_8 = new BlockItem(NumbersBlocks.LETTER_8, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_9 = new BlockItem(NumbersBlocks.LETTER_9, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_1"), LETTER_1);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_2"), LETTER_2);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_3"), LETTER_3);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_4"), LETTER_4);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_5"), LETTER_5);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_6"), LETTER_6);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_7"), LETTER_7);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_8"), LETTER_8);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_9"), LETTER_9);
    }
}
