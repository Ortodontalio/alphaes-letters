package com.ortodontalio.alphaesletters.misc;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MiscBlockItems {

    public static final BlockItem LETTER_DASH = new BlockItem(MiscBlocks.LETTER_DASH, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_APOSTROPHE = new BlockItem(MiscBlocks.LETTER_APOSTROPHE, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_QUEST = new BlockItem(MiscBlocks.LETTER_QUEST, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_EXCLAM = new BlockItem(MiscBlocks.LETTER_EXCLAM, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_BOT_UP_LINE = new BlockItem(MiscBlocks.LETTER_BOT_UP_LINE, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_LEFT_RIGHT_LINE = new BlockItem(MiscBlocks.LETTER_LEFT_RIGHT_LINE, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_CROSS = new BlockItem(MiscBlocks.LETTER_CROSS, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_DOWN_AR_NO = new BlockItem(MiscBlocks.LETTER_DOWN_AR_NO, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_UP_AR_NO = new BlockItem(MiscBlocks.LETTER_UP_AR_NO, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_LEFT_AR_NO = new BlockItem(MiscBlocks.LETTER_LEFT_AR_NO, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_RIGHT_AR_NO = new BlockItem(MiscBlocks.LETTER_RIGHT_AR_NO, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_DOWN_AR = new BlockItem(MiscBlocks.LETTER_DOWN_AR, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_DOWN_CROSS = new BlockItem(MiscBlocks.LETTER_DOWN_CROSS, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_FIRST_QUARTER = new BlockItem(MiscBlocks.LETTER_FIRST_QUARTER, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_FOURTH_QUARTER = new BlockItem(MiscBlocks.LETTER_FOURTH_QUARTER, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_LEFT_AR = new BlockItem(MiscBlocks.LETTER_LEFT_AR, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_LEFT_CROSS = new BlockItem(MiscBlocks.LETTER_LEFT_CROSS, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_RIGHT_AR = new BlockItem(MiscBlocks.LETTER_RIGHT_AR, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_RIGHT_CROSS = new BlockItem(MiscBlocks.LETTER_RIGHT_CROSS, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_SECOND_QUARTER = new BlockItem(MiscBlocks.LETTER_SECOND_QUARTER, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_THIRD_QUARTER = new BlockItem(MiscBlocks.LETTER_THIRD_QUARTER, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_UP_CROSS = new BlockItem(MiscBlocks.LETTER_UP_CROSS, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_UP_AR = new BlockItem(MiscBlocks.LETTER_UP_AR, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_RETURN_LEFT = new BlockItem(MiscBlocks.LETTER_RETURN_LEFT, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_RETURN_RIGHT = new BlockItem(MiscBlocks.LETTER_RETURN_RIGHT, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_RIVER = new BlockItem(MiscBlocks.LETTER_RIVER, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));
    public static final BlockItem LETTER_CYR_RIVER = new BlockItem(MiscBlocks.LETTER_CYR_RIVER, new Item.Settings()
            .group(AlphaesLetters.ALPHAES_MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_dash"), LETTER_DASH);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_apostrophe"), LETTER_APOSTROPHE);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_quest"), LETTER_QUEST);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_exclam"), LETTER_EXCLAM);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_bot_up_line"), LETTER_BOT_UP_LINE);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_left_right_line"), LETTER_LEFT_RIGHT_LINE);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_cross"), LETTER_CROSS);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_down_ar_no"), LETTER_DOWN_AR_NO);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_up_ar_no"), LETTER_UP_AR_NO);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_left_ar_no"), LETTER_LEFT_AR_NO);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_right_ar_no"), LETTER_RIGHT_AR_NO);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_down_ar"), LETTER_DOWN_AR);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_down_cross"), LETTER_DOWN_CROSS);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_first_quarter"), LETTER_FIRST_QUARTER);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_fourth_quarter"), LETTER_FOURTH_QUARTER);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_left_ar"), LETTER_LEFT_AR);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_left_cross"), LETTER_LEFT_CROSS);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_right_ar"), LETTER_RIGHT_AR);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_right_cross"), LETTER_RIGHT_CROSS);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_second_quarter"), LETTER_SECOND_QUARTER);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_third_quarter"), LETTER_THIRD_QUARTER);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_up_cross"), LETTER_UP_CROSS);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_up_ar"), LETTER_UP_AR);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_return_left"), LETTER_RETURN_LEFT);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_return_right"), LETTER_RETURN_RIGHT);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_river"), LETTER_RIVER);
        Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, "letter_cyr_river"), LETTER_CYR_RIVER);
    }
}
