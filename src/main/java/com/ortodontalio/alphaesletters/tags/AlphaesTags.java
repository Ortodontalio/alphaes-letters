package com.ortodontalio.alphaesletters.tags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesTags {

    public static class Blocks {
        public static final TagKey<Block> LETTER_BLOCKS = createTag("letter_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CONCRETE_POWDER_BLOCKS = createTag("concrete_powder_blocks");
        public static final TagKey<Item> FARBA_ITEMS = createTag("farba_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, name));
        }
    }

}