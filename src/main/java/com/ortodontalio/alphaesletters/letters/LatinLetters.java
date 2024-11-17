package com.ortodontalio.alphaesletters.letters;

import com.ortodontalio.alphaesletters.codegen.LatinLettersRegistrator;
import com.ortodontalio.alphaesletters.common.LetterSpec;
import com.ortodontalio.annotations.LettersDefinition;
import net.minecraft.block.Block;

@LettersDefinition
public enum LatinLetters implements LetterSpec {
    LETTER_A(LatinLettersRegistrator.LETTER_A),
    LETTER_B(LatinLettersRegistrator.LETTER_B),
    LETTER_C(LatinLettersRegistrator.LETTER_C),
    LETTER_CH(LatinLettersRegistrator.LETTER_CH),
    LETTER_D(LatinLettersRegistrator.LETTER_D),
    LETTER_E(LatinLettersRegistrator.LETTER_E),
    LETTER_ENE(LatinLettersRegistrator.LETTER_ENE),
    LETTER_F(LatinLettersRegistrator.LETTER_F),
    LETTER_G(LatinLettersRegistrator.LETTER_G),
    LETTER_H(LatinLettersRegistrator.LETTER_H),
    LETTER_I(LatinLettersRegistrator.LETTER_H),
    LETTER_J(LatinLettersRegistrator.LETTER_J),
    LETTER_K(LatinLettersRegistrator.LETTER_K),
    LETTER_L(LatinLettersRegistrator.LETTER_L),
    LETTER_M(LatinLettersRegistrator.LETTER_M),
    LETTER_N(LatinLettersRegistrator.LETTER_N),
    LETTER_O(LatinLettersRegistrator.LETTER_O),
    LETTER_P(LatinLettersRegistrator.LETTER_P),
    LETTER_Q(LatinLettersRegistrator.LETTER_Q),
    LETTER_R(LatinLettersRegistrator.LETTER_R),
    LETTER_S(LatinLettersRegistrator.LETTER_S),
    LETTER_SZ(LatinLettersRegistrator.LETTER_SZ),
    LETTER_T(LatinLettersRegistrator.LETTER_T),
    LETTER_U(LatinLettersRegistrator.LETTER_U),
    LETTER_V(LatinLettersRegistrator.LETTER_V),
    LETTER_W(LatinLettersRegistrator.LETTER_W),
    LETTER_X(LatinLettersRegistrator.LETTER_X),
    LETTER_Y(LatinLettersRegistrator.LETTER_Y),
    LETTER_Z(LatinLettersRegistrator.LETTER_Z);

    private final Block block;

    LatinLetters(Block block) {
        this.block = block;
    }

    @Override
    public Block getBlock() {
        return block;
    }
}
