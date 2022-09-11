package com.ortodontalio.alphaesletters.numbers;

import com.ortodontalio.alphaesletters.util.BlockRegistrator;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import net.minecraft.block.Block;

public class NumbersBlocks extends BlockRegistrator {

    public static final Block LETTER_1 = new LetterBasic();
    public static final Block LETTER_2 = new LetterBasic();
    public static final Block LETTER_3 = new LetterBasic();
    public static final Block LETTER_4 = new LetterBasic();
    public static final Block LETTER_5 = new LetterBasic();
    public static final Block LETTER_6 = new LetterBasic();
    public static final Block LETTER_7 = new LetterBasic();
    public static final Block LETTER_8 = new LetterBasic();
    public static final Block LETTER_9 = new LetterBasic();

    public static void registerBlocks() {
        registerBlocks(NumbersBlocks.class);
    }
}
