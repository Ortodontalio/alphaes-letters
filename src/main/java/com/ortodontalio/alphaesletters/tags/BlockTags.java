package com.ortodontalio.alphaesletters.tags;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class BlockTags {

    public static final Tag<Block> LETTER_BLOCKS = TagFactory.BLOCK.create(
            new Identifier("alphaesletters", "letter_blocks"));
}