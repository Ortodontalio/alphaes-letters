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
import com.ortodontalio.alphaesletters.tech.TechBlockItems;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
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
            return Enum.valueOf(lettersGroup, Optional.ofNullable(name).map(String::toUpperCase).orElse(""));
        } catch (IllegalArgumentException ex) {
            return (Enum<T>) MiscLetters.NONE;
        }
    }

    public static Optional<Block> findBlockByLetter(LetterSpec letter) {
        return Stream.of(CyrillicLetters.values(), LatinLetters.values(), MinecraftLetters.values(), MiscLetters.values())
                .flatMap(Arrays::stream)
                .filter(lt -> lt.equals(letter))
                .findFirst()
                .map(LetterSpec::getBlock);
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
        return Stream.of(findLetterByBlockNameInGroup(CyrillicLetters.class, name),
                        findLetterByBlockNameInGroup(LatinLetters.class, name),
                        findLetterByBlockNameInGroup(MinecraftLetters.class, name),
                        findLetterByBlockNameInGroup(MiscLetters.class, name))
                .filter(letter -> !MiscLetters.NONE.equals(letter))
                .map(LetterSpec.class::cast)
                .findFirst()
                .orElse(MiscLetters.NONE);
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
        blocks.addAll(getAllLetterConcretesBlocks());
        blocks.addAll(getAllExfoliatedConcreteBlocks());
        blocks.addAll(getAllLetterConcretesWithBarsBlocks());
        blocks.add(TechBlocks.CONCRETE_WITH_BARS);
        blocks.add(TechBlocks.DYEING_MACHINE);
        blocks.add(TechBlocks.IRON_FENCE);
        blocks.add(TechBlocks.IRON_FENCE_GATE);
        return blocks.toArray(Block[]::new);
    }

    public static List<Block> getAllLetterConcretesBlocks() {
        return List.of(
                TechBlocks.WHITE_LETTER_CONCRETE,
                TechBlocks.ORANGE_LETTER_CONCRETE,
                TechBlocks.MAGENTA_LETTER_CONCRETE,
                TechBlocks.LIGHT_BLUE_LETTER_CONCRETE,
                TechBlocks.YELLOW_LETTER_CONCRETE,
                TechBlocks.LIME_LETTER_CONCRETE,
                TechBlocks.PINK_LETTER_CONCRETE,
                TechBlocks.GRAY_LETTER_CONCRETE,
                TechBlocks.LIGHT_GRAY_LETTER_CONCRETE,
                TechBlocks.CYAN_LETTER_CONCRETE,
                TechBlocks.PURPLE_LETTER_CONCRETE,
                TechBlocks.LETTER_CONCRETE,
                TechBlocks.BROWN_LETTER_CONCRETE,
                TechBlocks.GREEN_LETTER_CONCRETE,
                TechBlocks.RED_LETTER_CONCRETE,
                TechBlocks.BLACK_LETTER_CONCRETE
        );
    }

    public static List<Block> getAllLetterConcretesWithBarsBlocks() {
        return List.of(
                TechBlocks.CONCRETE_WITH_BARS,
                TechBlocks.ORANGE_CONCRETE_WITH_BARS,
                TechBlocks.MAGENTA_CONCRETE_WITH_BARS,
                TechBlocks.LIGHT_BLUE_CONCRETE_WITH_BARS,
                TechBlocks.YELLOW_CONCRETE_WITH_BARS,
                TechBlocks.LIME_CONCRETE_WITH_BARS,
                TechBlocks.PINK_CONCRETE_WITH_BARS,
                TechBlocks.GRAY_CONCRETE_WITH_BARS,
                TechBlocks.LIGHT_GRAY_CONCRETE_WITH_BARS,
                TechBlocks.CYAN_CONCRETE_WITH_BARS,
                TechBlocks.PURPLE_CONCRETE_WITH_BARS,
                TechBlocks.BLUE_CONCRETE_WITH_BARS,
                TechBlocks.BROWN_CONCRETE_WITH_BARS,
                TechBlocks.GREEN_CONCRETE_WITH_BARS,
                TechBlocks.RED_CONCRETE_WITH_BARS,
                TechBlocks.BLACK_CONCRETE_WITH_BARS
        );
    }

    public static Item[] getAllLetterConcretesBlockItems() {
        return List.of(
                TechBlockItems.WHITE_LETTER_CONCRETE,
                TechBlockItems.ORANGE_LETTER_CONCRETE,
                TechBlockItems.MAGENTA_LETTER_CONCRETE,
                TechBlockItems.LIGHT_BLUE_LETTER_CONCRETE,
                TechBlockItems.YELLOW_LETTER_CONCRETE,
                TechBlockItems.LIME_LETTER_CONCRETE,
                TechBlockItems.PINK_LETTER_CONCRETE,
                TechBlockItems.GRAY_LETTER_CONCRETE,
                TechBlockItems.LIGHT_GRAY_LETTER_CONCRETE,
                TechBlockItems.CYAN_LETTER_CONCRETE,
                TechBlockItems.PURPLE_LETTER_CONCRETE,
                TechBlockItems.LETTER_CONCRETE,
                TechBlockItems.BROWN_LETTER_CONCRETE,
                TechBlockItems.GREEN_LETTER_CONCRETE,
                TechBlockItems.RED_LETTER_CONCRETE,
                TechBlockItems.BLACK_LETTER_CONCRETE
        ).toArray(Item[]::new);
    }

    public static Item[] getAllLetterExfoliatedConcretesBlockItems() {
        return List.of(
                TechBlockItems.WHITE_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.ORANGE_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.MAGENTA_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.LIGHT_BLUE_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.YELLOW_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.LIME_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.PINK_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.GRAY_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.LIGHT_GRAY_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.CYAN_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.PURPLE_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.BROWN_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.GREEN_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.RED_LETTER_EXFOLIATED_CONCRETE,
                TechBlockItems.BLACK_LETTER_EXFOLIATED_CONCRETE
        ).toArray(Item[]::new);
    }

    public static List<Block> getAllLetterPowdersBlocks() {
        return List.of(
                TechBlocks.LETTER_POWDER,
                TechBlocks.ORANGE_LETTER_POWDER,
                TechBlocks.MAGENTA_LETTER_POWDER,
                TechBlocks.LIGHT_BLUE_LETTER_POWDER,
                TechBlocks.YELLOW_LETTER_POWDER,
                TechBlocks.LIME_LETTER_POWDER,
                TechBlocks.PINK_LETTER_POWDER,
                TechBlocks.GRAY_LETTER_POWDER,
                TechBlocks.LIGHT_GRAY_LETTER_POWDER,
                TechBlocks.CYAN_LETTER_POWDER,
                TechBlocks.PURPLE_LETTER_POWDER,
                TechBlocks.BLUE_LETTER_POWDER,
                TechBlocks.BROWN_LETTER_POWDER,
                TechBlocks.GREEN_LETTER_POWDER,
                TechBlocks.RED_LETTER_POWDER,
                TechBlocks.BLACK_LETTER_POWDER
        );
    }

    public static Item[] getAllLetterPowdersBlockItems() {
        return List.of(
                TechBlockItems.LETTER_POWDER,
                TechBlockItems.ORANGE_LETTER_POWDER,
                TechBlockItems.MAGENTA_LETTER_POWDER,
                TechBlockItems.LIGHT_BLUE_LETTER_POWDER,
                TechBlockItems.YELLOW_LETTER_POWDER,
                TechBlockItems.LIME_LETTER_POWDER,
                TechBlockItems.PINK_LETTER_POWDER,
                TechBlockItems.GRAY_LETTER_POWDER,
                TechBlockItems.LIGHT_GRAY_LETTER_POWDER,
                TechBlockItems.CYAN_LETTER_POWDER,
                TechBlockItems.PURPLE_LETTER_POWDER,
                TechBlockItems.BLUE_LETTER_POWDER,
                TechBlockItems.BROWN_LETTER_POWDER,
                TechBlockItems.GREEN_LETTER_POWDER,
                TechBlockItems.RED_LETTER_POWDER,
                TechBlockItems.BLACK_LETTER_POWDER
        ).toArray(Item[]::new);
    }

    public static Item[] getAllConcretesWithBarsBlockItems() {
        return List.of(
                TechBlockItems.CONCRETE_WITH_BARS,
                TechBlockItems.ORANGE_CONCRETE_WITH_BARS,
                TechBlockItems.MAGENTA_CONCRETE_WITH_BARS,
                TechBlockItems.LIGHT_BLUE_CONCRETE_WITH_BARS,
                TechBlockItems.YELLOW_CONCRETE_WITH_BARS,
                TechBlockItems.LIME_CONCRETE_WITH_BARS,
                TechBlockItems.PINK_CONCRETE_WITH_BARS,
                TechBlockItems.GRAY_CONCRETE_WITH_BARS,
                TechBlockItems.LIGHT_GRAY_CONCRETE_WITH_BARS,
                TechBlockItems.CYAN_CONCRETE_WITH_BARS,
                TechBlockItems.PURPLE_CONCRETE_WITH_BARS,
                TechBlockItems.BLUE_CONCRETE_WITH_BARS,
                TechBlockItems.BROWN_CONCRETE_WITH_BARS,
                TechBlockItems.GREEN_CONCRETE_WITH_BARS,
                TechBlockItems.RED_CONCRETE_WITH_BARS,
                TechBlockItems.BLACK_CONCRETE_WITH_BARS
        ).toArray(Item[]::new);
    }

    public static Block[] getAllLetterPowders() {
        return getAllLetterPowdersBlocks().toArray(Block[]::new);
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

    public static List<Block> getAllConcretePowdersBlocks() {
        return List.of(
                Blocks.WHITE_CONCRETE_POWDER,
                Blocks.ORANGE_CONCRETE_POWDER,
                Blocks.MAGENTA_CONCRETE_POWDER,
                Blocks.LIGHT_BLUE_CONCRETE_POWDER,
                Blocks.YELLOW_CONCRETE_POWDER,
                Blocks.LIME_CONCRETE_POWDER,
                Blocks.PINK_CONCRETE_POWDER,
                Blocks.GRAY_CONCRETE_POWDER,
                Blocks.LIGHT_GRAY_CONCRETE_POWDER,
                Blocks.CYAN_CONCRETE_POWDER,
                Blocks.PURPLE_CONCRETE_POWDER,
                Blocks.BLUE_CONCRETE_POWDER,
                Blocks.BROWN_CONCRETE_POWDER,
                Blocks.GREEN_CONCRETE_POWDER,
                Blocks.RED_CONCRETE_POWDER,
                Blocks.BLACK_CONCRETE_POWDER
        );
    }

    public static List<Block> getAllConcreteBlocks() {
        return List.of(
                Blocks.WHITE_CONCRETE,
                Blocks.ORANGE_CONCRETE,
                Blocks.MAGENTA_CONCRETE,
                Blocks.LIGHT_BLUE_CONCRETE,
                Blocks.YELLOW_CONCRETE,
                Blocks.LIME_CONCRETE,
                Blocks.PINK_CONCRETE,
                Blocks.GRAY_CONCRETE,
                Blocks.LIGHT_GRAY_CONCRETE,
                Blocks.CYAN_CONCRETE,
                Blocks.PURPLE_CONCRETE,
                Blocks.BLUE_CONCRETE,
                Blocks.BROWN_CONCRETE,
                Blocks.GREEN_CONCRETE,
                Blocks.RED_CONCRETE,
                Blocks.BLACK_CONCRETE
        );
    }

    public static List<Block> getAllExfoliatedConcreteBlocks() {
        return List.of(
                TechBlocks.WHITE_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.ORANGE_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.MAGENTA_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.LIGHT_BLUE_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.YELLOW_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.LIME_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.PINK_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.GRAY_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.LIGHT_GRAY_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.CYAN_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.PURPLE_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.BROWN_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.GREEN_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.RED_LETTER_EXFOLIATED_CONCRETE,
                TechBlocks.BLACK_LETTER_EXFOLIATED_CONCRETE
        );
    }
}
