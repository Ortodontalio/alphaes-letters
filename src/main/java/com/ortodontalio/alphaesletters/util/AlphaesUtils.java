package com.ortodontalio.alphaesletters.util;

import com.ortodontalio.alphaesletters.cyrillic.CyrillicBlockItems;
import com.ortodontalio.alphaesletters.cyrillic.CyrillicBlocks;
import com.ortodontalio.alphaesletters.latin.LatinBlockItems;
import com.ortodontalio.alphaesletters.latin.LatinBlocks;
import com.ortodontalio.alphaesletters.misc.MiscBlockItems;
import com.ortodontalio.alphaesletters.misc.MiscBlocks;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AlphaesUtils {

    private static final Logger LOGGER = Logger.getLogger(AlphaesUtils.class.getName());

    /**
     * Method for getting all blocks from the block registrator class.
     * <i>NOTE:</i> <b>java:S6204</b> is suppressed, since it is needed to use
     * modifiable list for further operations.
     *
     * @param blockClass class with blocks, implements {@link BlockRegistrator}.
     * @param <T>        Block registrable type.
     * @return all blocks from the class.
     */
    @SuppressWarnings("java:S6204")
    public static <T extends BlockRegistrator> List<Block> getAllBlocks(Class<T> blockClass) {
        return Arrays.stream(blockClass.getDeclaredFields())
                .map(field -> {
                    try {
                        return (Block) field.get(null);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, String.format("Cannot get block: %s", e.getMessage()));
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Method for getting all block items from the block items registrator class.
     * <i>NOTE:</i> <b>java:S6204</b> is suppressed, since it is needed to use
     * modifiable list for further operations.
     *
     * @param itemClass class with blocks, implements {@link BlockItemRegistrator}.
     * @param <T>       Block items registrable type.
     * @return all block items from the class.
     */
    @SuppressWarnings("java:S6204")
    public static <T extends BlockItemRegistrator> List<BlockItem> getAllBlockItems(Class<T> itemClass) {
        return Arrays.stream(itemClass.getDeclaredFields())
                .map(field -> {
                    try {
                        return (BlockItem) field.get(null);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, String.format("Cannot get block item: %s", e.getMessage()));
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static Block[] getAllBlocks() {
        List<Block> blocks = getAllBlocks(LatinBlocks.class);
        blocks.addAll(getAllBlocks(CyrillicBlocks.class));
        blocks.addAll(getAllBlocks(MiscBlocks.class));
        blocks.addAll(getAllBlocks(TechBlocks.class));
        return blocks.toArray(Block[]::new);
    }

    public static BlockItem[] getAllLetterBlockItems() {
        List<BlockItem> blockItems = getAllBlockItems(LatinBlockItems.class);
        blockItems.addAll(getAllBlockItems(CyrillicBlockItems.class));
        blockItems.addAll(getAllBlockItems(MiscBlockItems.class));
        return blockItems.toArray(BlockItem[]::new);
    }

    public static Block[] getAllSolidBlocks() {
        List<Block> blocks = getAllBlocks(LatinBlocks.class);
        blocks.addAll(getAllBlocks(CyrillicBlocks.class));
        blocks.addAll(getAllBlocks(MiscBlocks.class));
        blocks.add(TechBlocks.LETTER_CONCRETE);
        blocks.add(TechBlocks.CROPPED_LETTER_CONCRETE);
        blocks.add(TechBlocks.CONCRETE_WITH_BARS);
        blocks.add(TechBlocks.DYEING_MACHINE);
        blocks.add(TechBlocks.IRON_FENCE);
        blocks.add(TechBlocks.IRON_FENCE_GATE);
        return blocks.toArray(Block[]::new);
    }

    public static Item[] getAllDyes() {
        List<Item> dyes = new ArrayList<>(16);
        dyes.add(Items.WHITE_DYE);
        dyes.add(Items.ORANGE_DYE);
        dyes.add(Items.MAGENTA_DYE);
        dyes.add(Items.LIGHT_BLUE_DYE);
        dyes.add(Items.YELLOW_DYE);
        dyes.add(Items.LIME_DYE);
        dyes.add(Items.PINK_DYE);
        dyes.add(Items.GRAY_DYE);
        dyes.add(Items.LIGHT_GRAY_DYE);
        dyes.add(Items.CYAN_DYE);
        dyes.add(Items.PURPLE_DYE);
        dyes.add(Items.BLUE_DYE);
        dyes.add(Items.BROWN_DYE);
        dyes.add(Items.GREEN_DYE);
        dyes.add(Items.RED_DYE);
        dyes.add(Items.BLACK_DYE);
        return dyes.toArray(Item[]::new);
    }

    public static Item[] getAllConcretePowders() {
        List<Item> dyes = new ArrayList<>(16);
        dyes.add(Items.WHITE_CONCRETE_POWDER);
        dyes.add(Items.ORANGE_CONCRETE_POWDER);
        dyes.add(Items.MAGENTA_CONCRETE_POWDER);
        dyes.add(Items.LIGHT_BLUE_CONCRETE_POWDER);
        dyes.add(Items.YELLOW_CONCRETE_POWDER);
        dyes.add(Items.LIME_CONCRETE_POWDER);
        dyes.add(Items.PINK_CONCRETE_POWDER);
        dyes.add(Items.GRAY_CONCRETE_POWDER);
        dyes.add(Items.LIGHT_GRAY_CONCRETE_POWDER);
        dyes.add(Items.CYAN_CONCRETE_POWDER);
        dyes.add(Items.PURPLE_CONCRETE_POWDER);
        dyes.add(Items.BLUE_CONCRETE_POWDER);
        dyes.add(Items.BROWN_CONCRETE_POWDER);
        dyes.add(Items.GREEN_CONCRETE_POWDER);
        dyes.add(Items.RED_CONCRETE_POWDER);
        dyes.add(Items.BLACK_CONCRETE_POWDER);
        return dyes.toArray(Item[]::new);
    }

    public static Item[] getAllConcrete() {
        List<Item> dyes = new ArrayList<>(16);
        dyes.add(Items.WHITE_CONCRETE);
        dyes.add(Items.ORANGE_CONCRETE);
        dyes.add(Items.MAGENTA_CONCRETE);
        dyes.add(Items.LIGHT_BLUE_CONCRETE);
        dyes.add(Items.YELLOW_CONCRETE);
        dyes.add(Items.LIME_CONCRETE);
        dyes.add(Items.PINK_CONCRETE);
        dyes.add(Items.GRAY_CONCRETE);
        dyes.add(Items.LIGHT_GRAY_CONCRETE);
        dyes.add(Items.CYAN_CONCRETE);
        dyes.add(Items.PURPLE_CONCRETE);
        dyes.add(Items.BLUE_CONCRETE);
        dyes.add(Items.BROWN_CONCRETE);
        dyes.add(Items.GREEN_CONCRETE);
        dyes.add(Items.RED_CONCRETE);
        dyes.add(Items.BLACK_CONCRETE);
        return dyes.toArray(Item[]::new);
    }
}
