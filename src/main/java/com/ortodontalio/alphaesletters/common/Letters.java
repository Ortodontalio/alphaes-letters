package com.ortodontalio.alphaesletters.common;

import com.ortodontalio.alphaesletters.cyrillic.CyrillicBlocks;
import com.ortodontalio.alphaesletters.latin.LatinBlocks;
import com.ortodontalio.alphaesletters.misc.MiscBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringIdentifiable;

import java.util.Locale;

public enum Letters implements StringIdentifiable {
    NONE(null),
    LETTER_1(MiscBlocks.LETTER_1),
    LETTER_2(MiscBlocks.LETTER_2),
    LETTER_3(MiscBlocks.LETTER_3),
    LETTER_4(MiscBlocks.LETTER_4),
    LETTER_5(MiscBlocks.LETTER_5),
    LETTER_6(MiscBlocks.LETTER_6),
    LETTER_7(MiscBlocks.LETTER_7),
    LETTER_8(MiscBlocks.LETTER_8),
    LETTER_9(MiscBlocks.LETTER_9),
    LETTER_A(LatinBlocks.LETTER_A),
    LETTER_APOSTROPHE(MiscBlocks.LETTER_APOSTROPHE),
    LETTER_B(LatinBlocks.LETTER_B),
    LETTER_BOT_UP_LINE(MiscBlocks.LETTER_BOT_UP_LINE),
    LETTER_C(LatinBlocks.LETTER_C),
    LETTER_CH(LatinBlocks.LETTER_CH),
    LETTER_CROSS(MiscBlocks.LETTER_CROSS),
    LETTER_CYR_AE(CyrillicBlocks.LETTER_CYR_AE),
    LETTER_CYR_B(CyrillicBlocks.LETTER_CYR_B),
    LETTER_CYR_C(CyrillicBlocks.LETTER_CYR_C),
    LETTER_CYR_CH(CyrillicBlocks.LETTER_CYR_CH),
    LETTER_CYR_D(CyrillicBlocks.LETTER_CYR_D),
    LETTER_CYR_DZHE(CyrillicBlocks.LETTER_CYR_DZHE),
    LETTER_CYR_DZHI(CyrillicBlocks.LETTER_CYR_DZHI),
    LETTER_CYR_E(CyrillicBlocks.LETTER_CYR_E),
    LETTER_CYR_EO(CyrillicBlocks.LETTER_CYR_EO),
    LETTER_CYR_F(CyrillicBlocks.LETTER_CYR_F),
    LETTER_CYR_G(CyrillicBlocks.LETTER_CYR_G),
    LETTER_CYR_GG(CyrillicBlocks.LETTER_CYR_GG),
    LETTER_CYR_GH(CyrillicBlocks.LETTER_CYR_GH),
    LETTER_CYR_HARD(CyrillicBlocks.LETTER_CYR_HARD),
    LETTER_CYR_I(CyrillicBlocks.LETTER_CYR_I),
    LETTER_CYR_II(CyrillicBlocks.LETTER_CYR_II),
    LETTER_CYR_IO(CyrillicBlocks.LETTER_CYR_IO),
    LETTER_CYR_J(CyrillicBlocks.LETTER_CYR_J),
    LETTER_CYR_JI(CyrillicBlocks.LETTER_CYR_JI),
    LETTER_CYR_KH(CyrillicBlocks.LETTER_CYR_KH),
    LETTER_CYR_L(CyrillicBlocks.LETTER_CYR_L),
    LETTER_CYR_NG(CyrillicBlocks.LETTER_CYR_NG),
    LETTER_CYR_P(CyrillicBlocks.LETTER_CYR_P),
    LETTER_CYR_RIVER(MiscBlocks.LETTER_CYR_RIVER),
    LETTER_CYR_SH(CyrillicBlocks.LETTER_CYR_SH),
    LETTER_CYR_SHCH(CyrillicBlocks.LETTER_CYR_SHCH),
    LETTER_CYR_SHKHA(CyrillicBlocks.LETTER_CYR_SHKHA),
    LETTER_CYR_SOFT(CyrillicBlocks.LETTER_CYR_SOFT),
    LETTER_CYR_U(CyrillicBlocks.LETTER_CYR_U),
    LETTER_CYR_UU(CyrillicBlocks.LETTER_CYR_UU),
    LETTER_CYR_W(CyrillicBlocks.LETTER_CYR_W),
    LETTER_CYR_X(CyrillicBlocks.LETTER_CYR_X),
    LETTER_CYR_Y(CyrillicBlocks.LETTER_CYR_Y),
    LETTER_CYR_YA(CyrillicBlocks.LETTER_CYR_YA),
    LETTER_CYR_YE(CyrillicBlocks.LETTER_CYR_YE),
    LETTER_CYR_YO(CyrillicBlocks.LETTER_CYR_YO),
    LETTER_CYR_YU(CyrillicBlocks.LETTER_CYR_YU),
    LETTER_CYR_Z(CyrillicBlocks.LETTER_CYR_Z),
    LETTER_CYR_ZH(CyrillicBlocks.LETTER_CYR_ZH),
    LETTER_D(LatinBlocks.LETTER_D),
    LETTER_DASH(MiscBlocks.LETTER_DASH),
    LETTER_DOWN_AR(MiscBlocks.LETTER_DOWN_AR),
    LETTER_DOWN_AR_NO(MiscBlocks.LETTER_DOWN_AR_NO),
    LETTER_DOWN_CROSS(MiscBlocks.LETTER_DOWN_CROSS),
    LETTER_E(LatinBlocks.LETTER_E),
    LETTER_ENE(LatinBlocks.LETTER_ENE),
    LETTER_EXCLAM(MiscBlocks.LETTER_EXCLAM),
    LETTER_F(LatinBlocks.LETTER_F),
    LETTER_FIRST_QUARTER(MiscBlocks.LETTER_FIRST_QUARTER),
    LETTER_FOURTH_QUARTER(MiscBlocks.LETTER_FOURTH_QUARTER),
    LETTER_G(LatinBlocks.LETTER_G),
    LETTER_H(LatinBlocks.LETTER_H),
    LETTER_I(LatinBlocks.LETTER_H),
    LETTER_J(LatinBlocks.LETTER_J),
    LETTER_K(LatinBlocks.LETTER_K),
    LETTER_L(LatinBlocks.LETTER_L),
    LETTER_LEFT_AR(MiscBlocks.LETTER_LEFT_AR),
    LETTER_LEFT_AR_NO(MiscBlocks.LETTER_LEFT_AR_NO),
    LETTER_LEFT_CROSS(MiscBlocks.LETTER_LEFT_CROSS),
    LETTER_LEFT_RIGHT_LINE(MiscBlocks.LETTER_LEFT_RIGHT_LINE),
    LETTER_M(LatinBlocks.LETTER_M),
    LETTER_N(LatinBlocks.LETTER_N),
    LETTER_O(LatinBlocks.LETTER_O),
    LETTER_P(LatinBlocks.LETTER_P),
    LETTER_Q(LatinBlocks.LETTER_Q),
    LETTER_QUEST(MiscBlocks.LETTER_QUEST),
    LETTER_R(LatinBlocks.LETTER_R),
    LETTER_RETURN_LEFT(MiscBlocks.LETTER_RETURN_LEFT),
    LETTER_RETURN_RIGHT(MiscBlocks.LETTER_RETURN_RIGHT),
    LETTER_RIGHT_AR(MiscBlocks.LETTER_RIGHT_AR),
    LETTER_RIGHT_AR_NO(MiscBlocks.LETTER_RIGHT_AR_NO),
    LETTER_RIGHT_CROSS(MiscBlocks.LETTER_RIGHT_CROSS),
    LETTER_RIVER(MiscBlocks.LETTER_RIVER),
    LETTER_S(LatinBlocks.LETTER_S),
    LETTER_SECOND_QUARTER(MiscBlocks.LETTER_SECOND_QUARTER),
    LETTER_SZ(LatinBlocks.LETTER_SZ),
    LETTER_T(LatinBlocks.LETTER_T),
    LETTER_THIRD_QUARTER(MiscBlocks.LETTER_THIRD_QUARTER),
    LETTER_U(LatinBlocks.LETTER_U),
    LETTER_UP_AR(MiscBlocks.LETTER_UP_AR),
    LETTER_UP_AR_NO(MiscBlocks.LETTER_UP_AR_NO),
    LETTER_UP_CROSS(MiscBlocks.LETTER_UP_CROSS),
    LETTER_V(LatinBlocks.LETTER_V),
    LETTER_W(LatinBlocks.LETTER_W),
    LETTER_X(LatinBlocks.LETTER_X),
    LETTER_Y(LatinBlocks.LETTER_Y),
    LETTER_Z(LatinBlocks.LETTER_Z),
    LETTER_LAKE(MiscBlocks.LETTER_LAKE),
    LETTER_CYR_LAKE(MiscBlocks.LETTER_CYR_LAKE);

    private final Block block;

    Letters(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public String asString() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public static Letters findLetterByBlock(ItemStack inHand) {
        return Letters.valueOf(inHand.getItem().toString().toUpperCase(Locale.ROOT));
    }
}
