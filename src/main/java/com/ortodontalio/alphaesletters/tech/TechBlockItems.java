package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TechBlockItems {

    public static final BlockItem LETTER_CONCRETE = new BlockItem(TechBlocks.LETTER_CONCRETE, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_TECH));
    public static final BlockItem DYEING_MACHINE = new BlockItem(TechBlocks.DYEING_MACHINE, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_TECH));
    public static final BlockItem LETTER_POWDER = new BlockItem(TechBlocks.LETTER_POWDER, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_TECH));
    public static final BlockItem STRIKETHROUGH_BLOCK = new BlockItem(TechBlocks.STRIKETHROUGH_BLOCK, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_TECH));
    public static final BlockItem IRON_FENCE = new BlockItem(TechBlocks.IRON_FENCE, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_TECH));
    public static final BlockItem IRON_FENCE_GATE = new BlockItem(TechBlocks.IRON_FENCE_GATE, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_TECH));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_concrete"), LETTER_CONCRETE);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "dyeing_machine"), DYEING_MACHINE);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_powder"), LETTER_POWDER);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "strikethrough_block"), STRIKETHROUGH_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "iron_fence"), IRON_FENCE);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "iron_fence_gate"), IRON_FENCE_GATE);
    }
}
