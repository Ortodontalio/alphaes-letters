package com.ortodontalio.alphaesletters.numbers;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NumbersBlocks {

    public static final Block LETTER_1 = new LetterBasic();
    public static final Block LETTER_2 = new LetterBasic();
    public static final Block LETTER_3 = new LetterBasic();
    public static final Block LETTER_4 = new LetterBasic();
    public static final Block LETTER_5 = new LetterBasic();
    public static final Block LETTER_6 = new LetterBasic();
    public static final Block LETTER_7 = new LetterBasic();
    public static final Block LETTER_8 = new LetterBasic();
    public static final Block LETTER_9 = new LetterBasic();

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_1"), LETTER_1);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_2"), LETTER_2);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_3"), LETTER_3);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_4"), LETTER_4);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_5"), LETTER_5);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_6"), LETTER_6);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_7"), LETTER_7);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_8"), LETTER_8);
        Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, "letter_9"), LETTER_9);

    }
}
