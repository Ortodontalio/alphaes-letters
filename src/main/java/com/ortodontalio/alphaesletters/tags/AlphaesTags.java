package com.ortodontalio.alphaesletters.tags;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesTags {

    private AlphaesTags() {
    }

    public static class Items {
        public static final TagKey<Item> CONCRETE_POWDER_BLOCKS = createTag("concrete_powder_blocks");
        public static final TagKey<Item> FARBA_ITEMS = createTag("farba_items");
        public static final TagKey<Item> LETTERS = createTag("letters");
        public static final TagKey<Item> AXES = createTag("axes");
        public static final TagKey<Item> HOES = createTag("hoes");

        private Items() {
        }

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, name));
        }
    }

}