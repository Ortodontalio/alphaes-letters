package com.ortodontalio.alphaesletters.cyrillic;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CyrillicBlocks {

    public static final Block LETTER_CYR_B = new LetterBasic();
    public static final Block LETTER_CYR_G = new LetterBasic();
    public static final Block LETTER_CYR_GG = new LetterBasic();
    public static final Block LETTER_CYR_D = new LetterBasic();
    public static final Block LETTER_CYR_YO = new LetterBasic();
    public static final Block LETTER_CYR_ZH = new LetterBasic();
    public static final Block LETTER_CYR_Z = new LetterBasic();
    public static final Block LETTER_CYR_I = new LetterBasic();
    public static final Block LETTER_CYR_JI = new LetterBasic();
    public static final Block LETTER_CYR_J = new LetterBasic();
    public static final Block LETTER_CYR_L = new LetterBasic();
    public static final Block LETTER_CYR_U = new LetterBasic();
    public static final Block LETTER_CYR_W = new LetterBasic();
    public static final Block LETTER_CYR_P = new LetterBasic();
    public static final Block LETTER_CYR_F = new LetterBasic();
    public static final Block LETTER_CYR_C = new LetterBasic();
    public static final Block LETTER_CYR_CH = new LetterBasic();
    public static final Block LETTER_CYR_SH = new LetterBasic();
    public static final Block LETTER_CYR_SHCH = new LetterBasic();
    public static final Block LETTER_CYR_SOFT = new LetterBasic();
    public static final Block LETTER_CYR_HARD = new LetterBasic();
    public static final Block LETTER_CYR_Y = new LetterBasic();
    public static final Block LETTER_CYR_E = new LetterBasic();
    public static final Block LETTER_CYR_YE = new LetterBasic();
    public static final Block LETTER_CYR_YU = new LetterBasic();
    public static final Block LETTER_CYR_YA = new LetterBasic();

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_b"), LETTER_CYR_B);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_g"), LETTER_CYR_G);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_gg"), LETTER_CYR_GG);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_d"), LETTER_CYR_D);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_yo"), LETTER_CYR_YO);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_zh"), LETTER_CYR_ZH);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_z"), LETTER_CYR_Z);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_i"), LETTER_CYR_I);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_ji"), LETTER_CYR_JI);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_j"), LETTER_CYR_J);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_l"), LETTER_CYR_L);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_p"), LETTER_CYR_P);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_u"), LETTER_CYR_U);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_w"), LETTER_CYR_W);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_f"), LETTER_CYR_F);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_c"), LETTER_CYR_C);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_ch"), LETTER_CYR_CH);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_sh"), LETTER_CYR_SH);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_shch"), LETTER_CYR_SHCH);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_soft"), LETTER_CYR_SOFT);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_hard"), LETTER_CYR_HARD);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_e"), LETTER_CYR_E);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_y"), LETTER_CYR_Y);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_ye"), LETTER_CYR_YE);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_yu"), LETTER_CYR_YU);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_ya"), LETTER_CYR_YA);
    }
}
