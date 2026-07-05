package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.stream.Stream;

public class TechBlockItems {

    public static final BlockItem LETTER_CONCRETE = getBlockItem(TechBlocks.LETTER_CONCRETE,
            "letter_concrete");
    public static final BlockItem WHITE_LETTER_CONCRETE = getBlockItem(TechBlocks.WHITE_LETTER_CONCRETE,
            "white_letter_concrete");
    public static final BlockItem ORANGE_LETTER_CONCRETE = getBlockItem(TechBlocks.ORANGE_LETTER_CONCRETE,
            "orange_letter_concrete");
    public static final BlockItem MAGENTA_LETTER_CONCRETE = getBlockItem(TechBlocks.MAGENTA_LETTER_CONCRETE,
            "magenta_letter_concrete");
    public static final BlockItem LIGHT_BLUE_LETTER_CONCRETE = getBlockItem(TechBlocks.LIGHT_BLUE_LETTER_CONCRETE,
            "light_blue_letter_concrete");
    public static final BlockItem YELLOW_LETTER_CONCRETE = getBlockItem(TechBlocks.YELLOW_LETTER_CONCRETE,
            "yellow_letter_concrete");
    public static final BlockItem LIME_LETTER_CONCRETE = getBlockItem(TechBlocks.LIME_LETTER_CONCRETE,
            "lime_letter_concrete");
    public static final BlockItem PINK_LETTER_CONCRETE = getBlockItem(TechBlocks.PINK_LETTER_CONCRETE,
            "pink_letter_concrete");
    public static final BlockItem GRAY_LETTER_CONCRETE = getBlockItem(TechBlocks.GRAY_LETTER_CONCRETE,
            "gray_letter_concrete");
    public static final BlockItem LIGHT_GRAY_LETTER_CONCRETE = getBlockItem(TechBlocks.LIGHT_GRAY_LETTER_CONCRETE,
            "light_gray_letter_concrete");
    public static final BlockItem CYAN_LETTER_CONCRETE = getBlockItem(TechBlocks.CYAN_LETTER_CONCRETE,
            "cyan_letter_concrete");
    public static final BlockItem PURPLE_LETTER_CONCRETE = getBlockItem(TechBlocks.PURPLE_LETTER_CONCRETE,
            "purple_letter_concrete");
    public static final BlockItem BROWN_LETTER_CONCRETE = getBlockItem(TechBlocks.BROWN_LETTER_CONCRETE,
            "brown_letter_concrete");
    public static final BlockItem GREEN_LETTER_CONCRETE = getBlockItem(TechBlocks.GREEN_LETTER_CONCRETE,
            "green_letter_concrete");
    public static final BlockItem RED_LETTER_CONCRETE = getBlockItem(TechBlocks.RED_LETTER_CONCRETE,
            "red_letter_concrete");
    public static final BlockItem BLACK_LETTER_CONCRETE = getBlockItem(TechBlocks.BLACK_LETTER_CONCRETE,
            "black_letter_concrete");

    public static final BlockItem LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.LETTER_EXFOLIATED_CONCRETE,
            "letter_exfoliated_concrete");
    public static final BlockItem WHITE_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.WHITE_LETTER_EXFOLIATED_CONCRETE,
            "white_letter_exfoliated_concrete");
    public static final BlockItem ORANGE_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.ORANGE_LETTER_EXFOLIATED_CONCRETE,
            "orange_letter_exfoliated_concrete");
    public static final BlockItem MAGENTA_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.MAGENTA_LETTER_EXFOLIATED_CONCRETE,
            "magenta_letter_exfoliated_concrete");
    public static final BlockItem LIGHT_BLUE_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.LIGHT_BLUE_LETTER_EXFOLIATED_CONCRETE,
            "light_blue_letter_exfoliated_concrete");
    public static final BlockItem YELLOW_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.YELLOW_LETTER_EXFOLIATED_CONCRETE,
            "yellow_letter_exfoliated_concrete");
    public static final BlockItem LIME_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.LIME_LETTER_EXFOLIATED_CONCRETE,
            "lime_letter_exfoliated_concrete");
    public static final BlockItem PINK_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.PINK_LETTER_EXFOLIATED_CONCRETE,
            "pink_letter_exfoliated_concrete");
    public static final BlockItem GRAY_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.GRAY_LETTER_EXFOLIATED_CONCRETE,
            "gray_letter_exfoliated_concrete");
    public static final BlockItem LIGHT_GRAY_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.LIGHT_GRAY_LETTER_EXFOLIATED_CONCRETE,
            "light_gray_letter_exfoliated_concrete");
    public static final BlockItem CYAN_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.CYAN_LETTER_EXFOLIATED_CONCRETE,
            "cyan_letter_exfoliated_concrete");
    public static final BlockItem PURPLE_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.PURPLE_LETTER_EXFOLIATED_CONCRETE,
            "purple_letter_exfoliated_concrete");
    public static final BlockItem BROWN_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.BROWN_LETTER_EXFOLIATED_CONCRETE,
            "brown_letter_exfoliated_concrete");
    public static final BlockItem GREEN_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.GREEN_LETTER_EXFOLIATED_CONCRETE,
            "green_letter_exfoliated_concrete");
    public static final BlockItem RED_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.RED_LETTER_EXFOLIATED_CONCRETE,
            "red_letter_exfoliated_concrete");
    public static final BlockItem BLACK_LETTER_EXFOLIATED_CONCRETE = getBlockItem(TechBlocks.BLACK_LETTER_EXFOLIATED_CONCRETE,
            "black_letter_exfoliated_concrete");

    //    public static final BlockItem CROPPED_LETTER_CONCRETE = new BlockItem(TechBlocks.CROPPED_LETTER_CONCRETE, new Item
//            .Settings()
//            .useBlockPrefixedTranslationKey()
//            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, "cropped_letter_concrete"))));
    public static final BlockItem DYEING_MACHINE = getBlockItem(TechBlocks.DYEING_MACHINE, "dyeing_machine");

    public static final BlockItem LETTER_POWDER = getBlockItem(TechBlocks.LETTER_POWDER, "letter_powder");
    public static final BlockItem BLUE_LETTER_POWDER = getBlockItem(TechBlocks.BLUE_LETTER_POWDER,
            "blue_letter_powder");
    public static final BlockItem ORANGE_LETTER_POWDER = getBlockItem(TechBlocks.ORANGE_LETTER_POWDER,
            "orange_letter_powder");
    public static final BlockItem MAGENTA_LETTER_POWDER = getBlockItem(TechBlocks.MAGENTA_LETTER_POWDER,
            "magenta_letter_powder");
    public static final BlockItem LIGHT_BLUE_LETTER_POWDER = getBlockItem(TechBlocks.LIGHT_BLUE_LETTER_POWDER,
            "light_blue_letter_powder");
    public static final BlockItem YELLOW_LETTER_POWDER = getBlockItem(TechBlocks.YELLOW_LETTER_POWDER,
            "yellow_letter_powder");
    public static final BlockItem LIME_LETTER_POWDER = getBlockItem(TechBlocks.LIME_LETTER_POWDER,
            "lime_letter_powder");
    public static final BlockItem PINK_LETTER_POWDER = getBlockItem(TechBlocks.PINK_LETTER_POWDER,
            "pink_letter_powder");
    public static final BlockItem GRAY_LETTER_POWDER = getBlockItem(TechBlocks.GRAY_LETTER_POWDER,
            "gray_letter_powder");
    public static final BlockItem LIGHT_GRAY_LETTER_POWDER = getBlockItem(TechBlocks.LIGHT_GRAY_LETTER_POWDER,
            "light_gray_letter_powder");
    public static final BlockItem CYAN_LETTER_POWDER = getBlockItem(TechBlocks.CYAN_LETTER_POWDER,
            "cyan_letter_powder");
    public static final BlockItem PURPLE_LETTER_POWDER = getBlockItem(TechBlocks.PURPLE_LETTER_POWDER,
            "purple_letter_powder");
    public static final BlockItem BROWN_LETTER_POWDER = getBlockItem(TechBlocks.BROWN_LETTER_POWDER,
            "brown_letter_powder");
    public static final BlockItem GREEN_LETTER_POWDER = getBlockItem(TechBlocks.GREEN_LETTER_POWDER,
            "green_letter_powder");
    public static final BlockItem RED_LETTER_POWDER = getBlockItem(TechBlocks.RED_LETTER_POWDER,
            "red_letter_powder");
    public static final BlockItem BLACK_LETTER_POWDER = getBlockItem(TechBlocks.BLACK_LETTER_POWDER,
            "black_letter_powder");

    public static final BlockItem CONCRETE_WITH_BARS = getBlockItem(TechBlocks.CONCRETE_WITH_BARS,
            "concrete_with_bars");
    public static final BlockItem BLUE_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.BLUE_CONCRETE_WITH_BARS,
            "blue_concrete_with_bars");
    public static final BlockItem ORANGE_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.ORANGE_CONCRETE_WITH_BARS,
            "orange_concrete_with_bars");
    public static final BlockItem MAGENTA_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.MAGENTA_CONCRETE_WITH_BARS,
            "magenta_concrete_with_bars");
    public static final BlockItem LIGHT_BLUE_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.LIGHT_BLUE_CONCRETE_WITH_BARS,
            "light_blue_concrete_with_bars");
    public static final BlockItem YELLOW_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.YELLOW_CONCRETE_WITH_BARS,
            "yellow_concrete_with_bars");
    public static final BlockItem LIME_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.LIME_CONCRETE_WITH_BARS,
            "lime_concrete_with_bars");
    public static final BlockItem PINK_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.PINK_CONCRETE_WITH_BARS,
            "pink_concrete_with_bars");
    public static final BlockItem GRAY_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.GRAY_CONCRETE_WITH_BARS,
            "gray_concrete_with_bars");
    public static final BlockItem LIGHT_GRAY_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.LIGHT_GRAY_CONCRETE_WITH_BARS,
            "light_gray_concrete_with_bars");
    public static final BlockItem CYAN_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.CYAN_CONCRETE_WITH_BARS,
            "cyan_concrete_with_bars");
    public static final BlockItem PURPLE_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.PURPLE_CONCRETE_WITH_BARS,
            "purple_concrete_with_bars");
    public static final BlockItem BROWN_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.BROWN_CONCRETE_WITH_BARS,
            "brown_concrete_with_bars");
    public static final BlockItem GREEN_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.GREEN_CONCRETE_WITH_BARS,
            "green_concrete_with_bars");
    public static final BlockItem RED_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.RED_CONCRETE_WITH_BARS,
            "red_concrete_with_bars");
    public static final BlockItem BLACK_CONCRETE_WITH_BARS = getBlockItem(TechBlocks.BLACK_CONCRETE_WITH_BARS,
            "black_concrete_with_bars");

    public static final BlockItem STRIKETHROUGH_BLOCK = getStrikethroughBlockItem();
    public static final BlockItem IRON_FENCE = getBlockItem(TechBlocks.IRON_FENCE, "iron_fence");
    public static final BlockItem IRON_FENCE_GATE = getBlockItem(TechBlocks.IRON_FENCE_GATE, "iron_fence_gate");

    private static BlockItem getBlockItem(Block concrete, String path) {
        var registryPath = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, path));
        return Registry.register(Registries.ITEM, registryPath, new BlockItem(concrete, new Item
                .Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(registryPath)));
    }

    private static BlockItem getStrikethroughBlockItem() {
        var registryPath = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, StrikethroughBlock.ID));
        return Registry.register(Registries.ITEM, registryPath, new BlockItem(TechBlocks.STRIKETHROUGH_BLOCK, new Item
                .Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(registryPath)) {
            @Override
            public void appendTooltip(ItemStack stack,
                                      Item.TooltipContext context,
                                      List<Text> tooltip,
                                      TooltipType type) {
                BlockStateComponent component = stack.get(DataComponentTypes.BLOCK_STATE);
                if (component != null) {
                    DyeColor color = component.getValue(StrikethroughBlock.COLOR);
                    if (color != null) {
                        tooltip.add(Text.literal(String.format(Text.translatable("tooltip.alphaesletters.color")
                                        .getString(),
                                color.asString())).styled(style -> style.withColor(color.getSignColor())
                        ));
                    }
                }
            }
        });
    }

    public static List<ItemStack> registerAll() {
        return Stream.of(TechBlockItems.class.getDeclaredFields())
                .map(field -> {
                    try {
                        return new ItemStack((ItemConvertible) field.get(null));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }
}
