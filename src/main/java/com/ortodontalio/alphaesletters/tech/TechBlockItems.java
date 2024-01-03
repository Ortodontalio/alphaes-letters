package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.util.BlockItemRegistrator;
import com.ortodontalio.alphaesletters.util.GroupRegistrable;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class TechBlockItems extends BlockItemRegistrator implements GroupRegistrable {

    public static final BlockItem LETTER_CONCRETE = new BlockItem(TechBlocks.LETTER_CONCRETE, new Item.Settings());
    public static final BlockItem CROPPED_LETTER_CONCRETE = new BlockItem(TechBlocks.CROPPED_LETTER_CONCRETE,
            new Item.Settings());
    public static final BlockItem DYEING_MACHINE = new BlockItem(TechBlocks.DYEING_MACHINE, new Item.Settings());
    public static final BlockItem LETTER_POWDER = new BlockItem(TechBlocks.LETTER_POWDER, new Item.Settings());
    public static final BlockItem CONCRETE_WITH_BARS = new BlockItem(TechBlocks.CONCRETE_WITH_BARS, new Item.Settings());
    public static final BlockItem STRIKETHROUGH_BLOCK = new BlockItem(TechBlocks.STRIKETHROUGH_BLOCK, new Item.Settings());
    public static final BlockItem IRON_FENCE = new BlockItem(TechBlocks.IRON_FENCE, new Item.Settings());
    public static final BlockItem IRON_FENCE_GATE = new BlockItem(TechBlocks.IRON_FENCE_GATE, new Item.Settings());

}
