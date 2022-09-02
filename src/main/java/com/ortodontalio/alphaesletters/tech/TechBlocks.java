package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.common.DyeingMachine;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.common.LetterPowder;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class TechBlocks {

    public static final Block LETTER_CONCRETE = new LetterBasic();
    public static final Block DYEING_MACHINE = new DyeingMachine();
    public static final Block LETTER_POWDER = new LetterPowder();

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_concrete"), LETTER_CONCRETE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "dyeing_machine"), DYEING_MACHINE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_powder"), LETTER_POWDER);
    }

}
