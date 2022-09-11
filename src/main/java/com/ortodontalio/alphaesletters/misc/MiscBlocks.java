package com.ortodontalio.alphaesletters.misc;

import com.ortodontalio.alphaesletters.util.BlockRegistrator;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import net.minecraft.block.Block;

public class MiscBlocks extends BlockRegistrator {

    public static final Block LETTER_DASH = new LetterBasic();
    public static final Block LETTER_APOSTROPHE = new LetterBasic();
    public static final Block LETTER_QUEST = new LetterBasic();
    public static final Block LETTER_EXCLAM = new LetterBasic();
    public static final Block LETTER_BOT_UP_LINE = new LetterBasic();
    public static final Block LETTER_LEFT_RIGHT_LINE = new LetterBasic();
    public static final Block LETTER_CROSS = new LetterBasic();
    public static final Block LETTER_DOWN_AR_NO = new LetterBasic();
    public static final Block LETTER_UP_AR_NO = new LetterBasic();
    public static final Block LETTER_LEFT_AR_NO = new LetterBasic();
    public static final Block LETTER_RIGHT_AR_NO = new LetterBasic();
    public static final Block LETTER_DOWN_AR = new LetterBasic();
    public static final Block LETTER_DOWN_CROSS = new LetterBasic();
    public static final Block LETTER_FIRST_QUARTER = new LetterBasic();
    public static final Block LETTER_FOURTH_QUARTER = new LetterBasic();
    public static final Block LETTER_LEFT_AR = new LetterBasic();
    public static final Block LETTER_LEFT_CROSS = new LetterBasic();
    public static final Block LETTER_RIGHT_AR = new LetterBasic();
    public static final Block LETTER_RIGHT_CROSS = new LetterBasic();
    public static final Block LETTER_SECOND_QUARTER = new LetterBasic();
    public static final Block LETTER_THIRD_QUARTER = new LetterBasic();
    public static final Block LETTER_UP_CROSS = new LetterBasic();
    public static final Block LETTER_UP_AR = new LetterBasic();
    public static final Block LETTER_RETURN_LEFT = new LetterBasic();
    public static final Block LETTER_RETURN_RIGHT = new LetterBasic();
    public static final Block LETTER_RIVER = new LetterBasic();
    public static final Block LETTER_CYR_RIVER = new LetterBasic();

    public static void registerBlocks() {
        registerBlocks(MiscBlocks.class);
    }
}
