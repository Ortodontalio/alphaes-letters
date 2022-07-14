package com.ortodontalio.alphaesletters.latin;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LatinBlocks {


    public static final Block LETTER_A = new LetterBasic();
    public static final Block LETTER_B = new LetterBasic();
    public static final Block LETTER_C = new LetterBasic();
    public static final Block LETTER_D = new LetterBasic();
    public static final Block LETTER_E = new LetterBasic();
    public static final Block LETTER_F = new LetterBasic();
    public static final Block LETTER_G = new LetterBasic();
    public static final Block LETTER_H = new LetterBasic();
    public static final Block LETTER_I = new LetterBasic();
    public static final Block LETTER_J = new LetterBasic();
    public static final Block LETTER_K = new LetterBasic();
    public static final Block LETTER_L = new LetterBasic();
    public static final Block LETTER_M = new LetterBasic();
    public static final Block LETTER_N = new LetterBasic();
    public static final Block LETTER_O = new LetterBasic();
    public static final Block LETTER_P = new LetterBasic();
    public static final Block LETTER_Q = new LetterBasic();
    public static final Block LETTER_R = new LetterBasic();
    public static final Block LETTER_S = new LetterBasic();
    public static final Block LETTER_T = new LetterBasic();
    public static final Block LETTER_U = new LetterBasic();
    public static final Block LETTER_V = new LetterBasic();
    public static final Block LETTER_W = new LetterBasic();
    public static final Block LETTER_X = new LetterBasic();
    public static final Block LETTER_Y = new LetterBasic();
    public static final Block LETTER_Z = new LetterBasic();

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_a"), LETTER_A);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_b"), LETTER_B);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_c"), LETTER_C);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_d"), LETTER_D);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_e"), LETTER_E);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_f"), LETTER_F);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_g"), LETTER_G);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_h"), LETTER_H);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_i"), LETTER_I);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_j"), LETTER_J);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_k"), LETTER_K);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_l"), LETTER_L);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_m"), LETTER_M);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_n"), LETTER_N);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_o"), LETTER_O);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_p"), LETTER_P);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_q"), LETTER_Q);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_r"), LETTER_R);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_s"), LETTER_S);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_t"), LETTER_T);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_u"), LETTER_U);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_v"), LETTER_V);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_w"), LETTER_W);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_x"), LETTER_X);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_y"), LETTER_Y);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_z"), LETTER_Z);

    }
}
