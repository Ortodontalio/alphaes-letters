package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.common.DyeingMachine;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.common.LetterPowder;
import com.ortodontalio.alphaesletters.common.StrikethroughBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class TechBlocks {

    public static final Block LETTER_CONCRETE = new LetterBasic();
    public static final Block DYEING_MACHINE = new DyeingMachine();
    public static final Block LETTER_POWDER = new LetterPowder();
    public static final Block STRIKETHROUGH_BLOCK = new StrikethroughBlock();
    public static final Block IRON_FENCE = new FenceBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY)
            .strength(5.0f,6.0f)
            .requiresTool());
    public static final Block IRON_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY)
            .strength(5.0f,6.0f)
            .requiresTool());

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_concrete"), LETTER_CONCRETE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "dyeing_machine"), DYEING_MACHINE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "letter_powder"), LETTER_POWDER);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "strikethrough_block"), STRIKETHROUGH_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "iron_fence"), IRON_FENCE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "iron_fence_gate"), IRON_FENCE_GATE);
    }

}
