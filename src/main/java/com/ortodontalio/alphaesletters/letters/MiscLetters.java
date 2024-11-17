package com.ortodontalio.alphaesletters.letters;

import com.ortodontalio.alphaesletters.codegen.MiscLettersRegistrator;
import com.ortodontalio.alphaesletters.common.LetterSpec;
import com.ortodontalio.annotations.LettersDefinition;
import net.minecraft.block.Block;

@LettersDefinition
public enum MiscLetters implements LetterSpec {
    NONE(null),
    LETTER_1(MiscLettersRegistrator.LETTER_1),
    LETTER_2(MiscLettersRegistrator.LETTER_2),
    LETTER_3(MiscLettersRegistrator.LETTER_3),
    LETTER_4(MiscLettersRegistrator.LETTER_4),
    LETTER_5(MiscLettersRegistrator.LETTER_5),
    LETTER_6(MiscLettersRegistrator.LETTER_6),
    LETTER_7(MiscLettersRegistrator.LETTER_7),
    LETTER_8(MiscLettersRegistrator.LETTER_8),
    LETTER_9(MiscLettersRegistrator.LETTER_9),
    LETTER_APOSTROPHE(MiscLettersRegistrator.LETTER_APOSTROPHE),
    LETTER_BOT_UP_LINE(MiscLettersRegistrator.LETTER_BOT_UP_LINE),
    LETTER_CROSS(MiscLettersRegistrator.LETTER_CROSS),
    LETTER_DASH(MiscLettersRegistrator.LETTER_DASH),
    LETTER_DOWN_AR(MiscLettersRegistrator.LETTER_DOWN_AR),
    LETTER_DOWN_AR_NO(MiscLettersRegistrator.LETTER_DOWN_AR_NO),
    LETTER_DOWN_CROSS(MiscLettersRegistrator.LETTER_DOWN_CROSS),
    LETTER_EXCLAM(MiscLettersRegistrator.LETTER_EXCLAM),
    LETTER_FIRST_QUARTER(MiscLettersRegistrator.LETTER_FIRST_QUARTER),
    LETTER_FOURTH_QUARTER(MiscLettersRegistrator.LETTER_FOURTH_QUARTER),
    LETTER_LEFT_AR(MiscLettersRegistrator.LETTER_LEFT_AR),
    LETTER_LEFT_AR_NO(MiscLettersRegistrator.LETTER_LEFT_AR_NO),
    LETTER_LEFT_CROSS(MiscLettersRegistrator.LETTER_LEFT_CROSS),
    LETTER_LEFT_RIGHT_LINE(MiscLettersRegistrator.LETTER_LEFT_RIGHT_LINE),
    LETTER_RETURN_LEFT(MiscLettersRegistrator.LETTER_RETURN_LEFT),
    LETTER_RETURN_RIGHT(MiscLettersRegistrator.LETTER_RETURN_RIGHT),
    LETTER_RIGHT_AR(MiscLettersRegistrator.LETTER_RIGHT_AR),
    LETTER_RIGHT_AR_NO(MiscLettersRegistrator.LETTER_RIGHT_AR_NO),
    LETTER_RIGHT_CROSS(MiscLettersRegistrator.LETTER_RIGHT_CROSS),
    LETTER_RIVER(MiscLettersRegistrator.LETTER_RIVER),
    LETTER_SECOND_QUARTER(MiscLettersRegistrator.LETTER_SECOND_QUARTER),
    LETTER_THIRD_QUARTER(MiscLettersRegistrator.LETTER_THIRD_QUARTER),
    LETTER_UP_AR(MiscLettersRegistrator.LETTER_UP_AR),
    LETTER_UP_AR_NO(MiscLettersRegistrator.LETTER_UP_AR_NO),
    LETTER_UP_CROSS(MiscLettersRegistrator.LETTER_UP_CROSS),
    LETTER_LAKE(MiscLettersRegistrator.LETTER_LAKE),
    LETTER_CYR_LAKE(MiscLettersRegistrator.LETTER_CYR_LAKE),
    LETTER_QUEST(MiscLettersRegistrator.LETTER_QUEST),
    LETTER_CYR_RIVER(MiscLettersRegistrator.LETTER_CYR_RIVER);

    private final Block block;

    MiscLetters(Block block) {
        this.block = block;
    }

    @Override
    public Block getBlock() {
        return block;
    }
}
