package com.ortodontalio.alphaesletters.misc;

import com.ortodontalio.alphaesletters.common.DyeingMachine;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.common.LetterPowder;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class MiscBlocks {

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
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_dash"), LETTER_DASH);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_apostrophe"), LETTER_APOSTROPHE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_quest"), LETTER_QUEST);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_exclam"), LETTER_EXCLAM);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_bot_up_line"), LETTER_BOT_UP_LINE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_left_right_line"), LETTER_LEFT_RIGHT_LINE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_cross"), LETTER_CROSS);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_down_ar_no"), LETTER_DOWN_AR_NO);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_up_ar_no"), LETTER_UP_AR_NO);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_left_ar_no"), LETTER_LEFT_AR_NO);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_right_ar_no"), LETTER_RIGHT_AR_NO);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_down_ar"), LETTER_DOWN_AR);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_down_cross"), LETTER_DOWN_CROSS);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_first_quarter"), LETTER_FIRST_QUARTER);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_fourth_quarter"), LETTER_FOURTH_QUARTER);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_left_ar"), LETTER_LEFT_AR);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_left_cross"), LETTER_LEFT_CROSS);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_right_ar"), LETTER_RIGHT_AR);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_right_cross"), LETTER_RIGHT_CROSS);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_second_quarter"), LETTER_SECOND_QUARTER);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_third_quarter"), LETTER_THIRD_QUARTER);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_up_cross"), LETTER_UP_CROSS);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_up_ar"), LETTER_UP_AR);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_return_left"), LETTER_RETURN_LEFT);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_return_right"), LETTER_RETURN_RIGHT);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_river"), LETTER_RIVER);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_cyr_river"), LETTER_CYR_RIVER);
    }

}
