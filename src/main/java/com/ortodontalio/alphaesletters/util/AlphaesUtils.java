package com.ortodontalio.alphaesletters.util;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.codegen.CyrillicLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.CyrillicLettersRegistrator;
import com.ortodontalio.alphaesletters.codegen.LatinLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.LatinLettersRegistrator;
import com.ortodontalio.alphaesletters.codegen.MinecraftLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.MinecraftLettersRegistrator;
import com.ortodontalio.alphaesletters.codegen.MiscLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.MiscLettersRegistrator;
import com.ortodontalio.alphaesletters.common.LetterSpec;
import com.ortodontalio.alphaesletters.letters.CyrillicLetters;
import com.ortodontalio.alphaesletters.letters.LatinLetters;
import com.ortodontalio.alphaesletters.letters.MinecraftLetters;
import com.ortodontalio.alphaesletters.letters.MiscLetters;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlphaesUtils {

    private static final Logger LOGGER = Logger.getLogger(AlphaesUtils.class.getName());

    private static <T extends Enum<T>> Enum<T> findLetterByBlockInGroup(Class<T> lettersGroup, ItemStack inHand) {
        try {
            return Enum.valueOf(lettersGroup, inHand.getItem().toString().replace(String.format("%s:", AlphaesLetters.MOD_ID), "").toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            return (Enum<T>) MiscLetters.NONE;
        }
    }

    private static <T extends Enum<T>> Enum<T> findLetterByBlockNameInGroup(Class<T> lettersGroup, String name) {
        try {
            return Enum.valueOf(lettersGroup, name.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return (Enum<T>) MiscLetters.NONE;
        }
    }

    public static LetterSpec findLetterByBlock(ItemStack inHand) {
        return Stream.of(findLetterByBlockInGroup(CyrillicLetters.class, inHand),
                        findLetterByBlockInGroup(LatinLetters.class, inHand),
                        findLetterByBlockInGroup(MinecraftLetters.class, inHand),
                        findLetterByBlockInGroup(MiscLetters.class, inHand))
                .filter(letter -> !MiscLetters.NONE.equals(letter))
                .map(LetterSpec.class::cast)
                .findFirst()
                .orElse(MiscLetters.NONE);
    }

    public static LetterSpec findLetterByName(String name) {
        return (LetterSpec) Set.of(findLetterByBlockNameInGroup(CyrillicLetters.class, name),
                        findLetterByBlockNameInGroup(LatinLetters.class, name),
                        findLetterByBlockNameInGroup(MinecraftLetters.class, name),
                        findLetterByBlockNameInGroup(MiscLetters.class, name))
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public static List<String> getAllLettersNames() {
        return getAllLetters().stream().map(LetterSpec::asString).toList();
    }

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
    public static <T> List<Block> getAllBlocks(Class<T> blockClass) {
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
    public static <T> List<BlockItem> getAllBlockItems(Class<T> itemClass) {
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
        List<Block> blocks = getAllBlocks(LatinLettersRegistrator.class);
        blocks.addAll(getAllBlocks(CyrillicLettersRegistrator.class));
        blocks.addAll(getAllBlocks(MiscLettersRegistrator.class));
        blocks.addAll(getAllBlocks(MinecraftLettersRegistrator.class));
        blocks.addAll(getAllBlocks(TechBlocks.class));
        return blocks.toArray(Block[]::new);
    }

    public static List<Block> getAllLetterBlocks() {
        List<Block> blocks = getAllBlocks(LatinLettersRegistrator.class);
        blocks.addAll(getAllBlocks(CyrillicLettersRegistrator.class));
        blocks.addAll(getAllBlocks(MiscLettersRegistrator.class));
        blocks.addAll(getAllBlocks(MinecraftLettersRegistrator.class));
        return blocks;
    }

    public static BlockItem[] getAllLetterBlockItems() {
        List<BlockItem> blockItems = getAllBlockItems(LatinLettersItemsRegistrator.class);
        blockItems.addAll(getAllBlockItems(CyrillicLettersItemsRegistrator.class));
        blockItems.addAll(getAllBlockItems(MiscLettersItemsRegistrator.class));
        blockItems.addAll(getAllBlockItems(MinecraftLettersItemsRegistrator.class));
        return blockItems.toArray(BlockItem[]::new);
    }

    public static List<LetterSpec> getAllLetters() {
        return Stream.of(LatinLetters.values(), CyrillicLetters.values(), MiscLetters.values(), MinecraftLetters.values())
                .flatMap(Arrays::stream)
                .map(LetterSpec.class::cast)
                .toList();
    }

    public static Block[] getAllSolidBlocks() {
        List<Block> blocks = getAllBlocks(LatinLettersRegistrator.class);
        blocks.addAll(getAllBlocks(CyrillicLettersRegistrator.class));
        blocks.addAll(getAllBlocks(MiscLettersRegistrator.class));
        blocks.addAll(getAllBlocks(MinecraftLettersRegistrator.class));
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
