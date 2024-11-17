package com.ortodontalio.alphaesletters.letters;

import com.ortodontalio.alphaesletters.codegen.MinecraftLettersRegistrator;
import com.ortodontalio.alphaesletters.common.LetterSpec;
import com.ortodontalio.annotations.LettersDefinition;
import net.minecraft.block.Block;

@LettersDefinition
public enum MinecraftLetters implements LetterSpec {
    LETTER_MC_A(MinecraftLettersRegistrator.LETTER_MC_A),
    LETTER_MC_B(MinecraftLettersRegistrator.LETTER_MC_B),
    LETTER_MC_C(MinecraftLettersRegistrator.LETTER_MC_C),
    LETTER_MC_D(MinecraftLettersRegistrator.LETTER_MC_D),
    LETTER_MC_E(MinecraftLettersRegistrator.LETTER_MC_E),
    LETTER_MC_F(MinecraftLettersRegistrator.LETTER_MC_F),
    LETTER_MC_G(MinecraftLettersRegistrator.LETTER_MC_G),
    LETTER_MC_H(MinecraftLettersRegistrator.LETTER_MC_H),
    LETTER_MC_I(MinecraftLettersRegistrator.LETTER_MC_I),
    LETTER_MC_J(MinecraftLettersRegistrator.LETTER_MC_J),
    LETTER_MC_K(MinecraftLettersRegistrator.LETTER_MC_K),
    LETTER_MC_L(MinecraftLettersRegistrator.LETTER_MC_L),
    LETTER_MC_M(MinecraftLettersRegistrator.LETTER_MC_M),
    LETTER_MC_N(MinecraftLettersRegistrator.LETTER_MC_N),
    LETTER_MC_O(MinecraftLettersRegistrator.LETTER_MC_O),
    LETTER_MC_P(MinecraftLettersRegistrator.LETTER_MC_P),
    LETTER_MC_Q(MinecraftLettersRegistrator.LETTER_MC_Q),
    LETTER_MC_R(MinecraftLettersRegistrator.LETTER_MC_R),
    LETTER_MC_S(MinecraftLettersRegistrator.LETTER_MC_S),
    LETTER_MC_T(MinecraftLettersRegistrator.LETTER_MC_T),
    LETTER_MC_U(MinecraftLettersRegistrator.LETTER_MC_U),
    LETTER_MC_V(MinecraftLettersRegistrator.LETTER_MC_V),
    LETTER_MC_W(MinecraftLettersRegistrator.LETTER_MC_W),
    LETTER_MC_X(MinecraftLettersRegistrator.LETTER_MC_X),
    LETTER_MC_Y(MinecraftLettersRegistrator.LETTER_MC_Y),
    LETTER_MC_Z(MinecraftLettersRegistrator.LETTER_MC_Z);

    private final Block block;

    MinecraftLetters(Block block) {
        this.block = block;
    }

    @Override
    public Block getBlock() {
        return block;
    }
}
