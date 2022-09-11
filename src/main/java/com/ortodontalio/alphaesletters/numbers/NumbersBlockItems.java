package com.ortodontalio.alphaesletters.numbers;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.util.BlockItemRegistrator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class NumbersBlockItems extends BlockItemRegistrator {

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

    public static void registerBlockItems() {
        registerBlocks(NumbersBlockItems.class);
    }
}
