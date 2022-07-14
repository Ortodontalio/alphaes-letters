package com.ortodontalio.alphaesletters.latin;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LatinBlockItems {

    public static final BlockItem LETTER_A = new BlockItem(LatinBlocks.LETTER_A, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_B = new BlockItem(LatinBlocks.LETTER_B, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_C = new BlockItem(LatinBlocks.LETTER_C, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_D = new BlockItem(LatinBlocks.LETTER_D, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_E = new BlockItem(LatinBlocks.LETTER_E, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_F = new BlockItem(LatinBlocks.LETTER_F, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_G = new BlockItem(LatinBlocks.LETTER_G, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_H = new BlockItem(LatinBlocks.LETTER_H, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_I = new BlockItem(LatinBlocks.LETTER_I, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_J = new BlockItem(LatinBlocks.LETTER_J, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_K = new BlockItem(LatinBlocks.LETTER_K, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_L = new BlockItem(LatinBlocks.LETTER_L, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_M = new BlockItem(LatinBlocks.LETTER_M, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_N = new BlockItem(LatinBlocks.LETTER_N, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_O = new BlockItem(LatinBlocks.LETTER_O, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_P = new BlockItem(LatinBlocks.LETTER_P, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_Q = new BlockItem(LatinBlocks.LETTER_Q, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_R = new BlockItem(LatinBlocks.LETTER_R, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_S = new BlockItem(LatinBlocks.LETTER_S, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_T = new BlockItem(LatinBlocks.LETTER_T, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_U = new BlockItem(LatinBlocks.LETTER_U, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_V = new BlockItem(LatinBlocks.LETTER_V, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_W = new BlockItem(LatinBlocks.LETTER_W, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_X = new BlockItem(LatinBlocks.LETTER_X, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_Y = new BlockItem(LatinBlocks.LETTER_Y, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));
    public static final BlockItem LETTER_Z = new BlockItem(LatinBlocks.LETTER_Z, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_LATIN));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_a"), LETTER_A);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_b"), LETTER_B);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_c"), LETTER_C);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_d"), LETTER_D);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_e"), LETTER_E);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_f"), LETTER_F);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_g"), LETTER_G);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_h"), LETTER_H);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_i"), LETTER_I);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_j"), LETTER_J);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_k"), LETTER_K);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_l"), LETTER_L);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_m"), LETTER_M);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_n"), LETTER_N);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_o"), LETTER_O);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_p"), LETTER_P);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_q"), LETTER_Q);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_r"), LETTER_R);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_s"), LETTER_S);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_t"), LETTER_T);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_u"), LETTER_U);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_v"), LETTER_V);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_w"), LETTER_W);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_x"), LETTER_X);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_y"), LETTER_Y);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_z"), LETTER_Z);
    }
}
