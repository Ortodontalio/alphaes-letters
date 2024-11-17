package com.ortodontalio.alphaesletters.letters;

import com.ortodontalio.alphaesletters.codegen.CyrillicLettersRegistrator;
import com.ortodontalio.alphaesletters.common.LetterSpec;
import com.ortodontalio.annotations.LettersDefinition;
import net.minecraft.block.Block;

@LettersDefinition
public enum CyrillicLetters implements LetterSpec {
    LETTER_CYR_AE(CyrillicLettersRegistrator.LETTER_CYR_AE),
    LETTER_CYR_B(CyrillicLettersRegistrator.LETTER_CYR_B),
    LETTER_CYR_C(CyrillicLettersRegistrator.LETTER_CYR_C),
    LETTER_CYR_CH(CyrillicLettersRegistrator.LETTER_CYR_CH),
    LETTER_CYR_D(CyrillicLettersRegistrator.LETTER_CYR_D),
    LETTER_CYR_DZHE(CyrillicLettersRegistrator.LETTER_CYR_DZHE),
    LETTER_CYR_DZHI(CyrillicLettersRegistrator.LETTER_CYR_DZHI),
    LETTER_CYR_E(CyrillicLettersRegistrator.LETTER_CYR_E),
    LETTER_CYR_EO(CyrillicLettersRegistrator.LETTER_CYR_EO),
    LETTER_CYR_F(CyrillicLettersRegistrator.LETTER_CYR_F),
    LETTER_CYR_G(CyrillicLettersRegistrator.LETTER_CYR_G),
    LETTER_CYR_GG(CyrillicLettersRegistrator.LETTER_CYR_GG),
    LETTER_CYR_GH(CyrillicLettersRegistrator.LETTER_CYR_GH),
    LETTER_CYR_HARD(CyrillicLettersRegistrator.LETTER_CYR_HARD),
    LETTER_CYR_I(CyrillicLettersRegistrator.LETTER_CYR_I),
    LETTER_CYR_II(CyrillicLettersRegistrator.LETTER_CYR_II),
    LETTER_CYR_IO(CyrillicLettersRegistrator.LETTER_CYR_IO),
    LETTER_CYR_J(CyrillicLettersRegistrator.LETTER_CYR_J),
    LETTER_CYR_JI(CyrillicLettersRegistrator.LETTER_CYR_JI),
    LETTER_CYR_KH(CyrillicLettersRegistrator.LETTER_CYR_KH),
    LETTER_CYR_L(CyrillicLettersRegistrator.LETTER_CYR_L),
    LETTER_CYR_NG(CyrillicLettersRegistrator.LETTER_CYR_NG),
    LETTER_CYR_P(CyrillicLettersRegistrator.LETTER_CYR_P),
    LETTER_CYR_SH(CyrillicLettersRegistrator.LETTER_CYR_SH),
    LETTER_CYR_SHCH(CyrillicLettersRegistrator.LETTER_CYR_SHCH),
    LETTER_CYR_SHKHA(CyrillicLettersRegistrator.LETTER_CYR_SHKHA),
    LETTER_CYR_SOFT(CyrillicLettersRegistrator.LETTER_CYR_SOFT),
    LETTER_CYR_U(CyrillicLettersRegistrator.LETTER_CYR_U),
    LETTER_CYR_UU(CyrillicLettersRegistrator.LETTER_CYR_UU),
    LETTER_CYR_W(CyrillicLettersRegistrator.LETTER_CYR_W),
    LETTER_CYR_X(CyrillicLettersRegistrator.LETTER_CYR_X),
    LETTER_CYR_Y(CyrillicLettersRegistrator.LETTER_CYR_Y),
    LETTER_CYR_YA(CyrillicLettersRegistrator.LETTER_CYR_YA),
    LETTER_CYR_YE(CyrillicLettersRegistrator.LETTER_CYR_YE),
    LETTER_CYR_YO(CyrillicLettersRegistrator.LETTER_CYR_YO),
    LETTER_CYR_YU(CyrillicLettersRegistrator.LETTER_CYR_YU),
    LETTER_CYR_Z(CyrillicLettersRegistrator.LETTER_CYR_Z),
    LETTER_CYR_ZH(CyrillicLettersRegistrator.LETTER_CYR_ZH);

    private final Block block;

    CyrillicLetters(Block block) {
        this.block = block;
    }

    @Override
    public Block getBlock() {
        return block;
    }
}
