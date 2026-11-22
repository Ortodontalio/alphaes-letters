package com.ortodontalio.alphaesletters.addon_system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.addon_system.models.Addon;
import com.ortodontalio.alphaesletters.addon_system.models.MinecraftModel;
import com.ortodontalio.alphaesletters.addon_system.models.TagModel;
import com.ortodontalio.alphaesletters.codegen.GroupRegistrator;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddonResourceLoader {
    public static final AddonResourcePack GENERATED_PACK = new AddonResourcePack("openletters_generated");
    private static final String ITEM_MODEL_TEMPLATE = "/mod_data/items_template.json";
    private static final String BLOCK_STATE_TEMPLATE = "/mod_data/blockstates_template.json";
    private static final String LOOT_TABLE_TEMPLATE = "/mod_data/loot_table_template.json";
    private static final String RECIPE_DEF_TEMPLATE = "/mod_data/recipe_def_template.json";
    private static final String RECIPE_CONF_TEMPLATE = "/mod_data/recipe_conf_template.json";
    private static final String AUTHOR_TOOLTIP = "blockProperty.alphaesletters.author";
    private static final String VERSION_TOOLTIP = "blockProperty.alphaesletters.version";
    private static final List<Block> ADDONS = new ArrayList<>();

    public static void checkAddonFolder() {
        var addonsPath = new File("./mods/openletters");
        addonsPath.mkdir();
        try (Stream<Path> addons = Files.walk(Paths.get(addonsPath.getPath()))) {
            ObjectMapper mapper = JsonMapper.builder()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                    .build();
            var addonsBlocks = addons
                    .filter(addon -> addon.getFileName().toString().endsWith(".json"))
                    .map(addon -> extractModelFromJson(mapper, addon))
                    .filter(Objects::nonNull)
                    .map(addon -> {
                        String addonName = getAddonNameWithoutExtension(addon);
                        if (generateBlockJsonFromModel(mapper, addon, addonName) &&
                                generateItemJson(addonName) &&
                                generateBlockStateJson(addonName) &&
                                generateLootTableJson(addonName) &&
                                generateRecipeDefJson(addonName) &&
                                generateRecipeConfJson(addonName)) {
                            return registerAddonLetter(addon);
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .toList();
            GroupRegistrator.registerGroup("customletters", addonsBlocks.stream()
                    .map(ItemStack::new)
                    .toList());
            ADDONS.clear();
            ADDONS.addAll(addonsBlocks);
            if (!ADDONS.isEmpty()) {
                appendBlocksToTagFromJson(mapper, "/data/minecraft/tags/block/mineable/pickaxe.json");
                appendBlocksToTagFromJson(mapper, "/data/minecraft/tags/block/needs_iron_tool.json");
            }
        } catch (IOException ignored) {
        }
    }

    private static boolean appendBlocksToTagFromJson(ObjectMapper mapper, String path) {
        try {
            TagModel tagModel = new TagModel();
            tagModel.setValues(ADDONS.stream()
                    .map(ad -> Registries.BLOCK.getId(ad).toString())
                    .collect(Collectors.toSet()));
            registerJson(path, mapper.writeValueAsString(tagModel));
            return true;
        } catch (IOException ignored) {
            // Skip broken addons.
            return false;
        }
    }

    private static boolean generateRecipeConfJson(String addonName) {
        readTemplateFromJar(RECIPE_CONF_TEMPLATE)
                .map(str -> String.format(str, addonName))
                .ifPresent(formatted ->
                        registerJson(formatPath("/data/alphaesletters/advancement/recipes/decorations", addonName),
                                formatted));
        return true;
    }

    private static boolean generateRecipeDefJson(String addonName) {
        readTemplateFromJar(RECIPE_DEF_TEMPLATE)
                .map(str -> String.format(str, addonName))
                .ifPresent(formatted ->
                        registerJson(formatPath("/data/alphaesletters/recipe", addonName), formatted));
        return true;
    }

    private static boolean generateLootTableJson(String addonName) {
        readTemplateFromJar(LOOT_TABLE_TEMPLATE)
                .map(str -> String.format(str, addonName))
                .ifPresent(formatted ->
                        registerJson(formatPath("/data/alphaesletters/loot_table/blocks", addonName), formatted));
        return true;
    }

    private static boolean generateBlockJsonFromModel(ObjectMapper mapper, Addon addon, String addonName) {
        try {
            registerJson(formatPath("/assets/alphaesletters/models/block", addonName),
                    mapper.writeValueAsString(addon.model()).toLowerCase());
        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }

    private static boolean generateItemJson(String addonName) {
        readTemplateFromJar(ITEM_MODEL_TEMPLATE)
                .map(str -> String.format(str, addonName))
                .ifPresent(formatted ->
                        registerJson(formatPath("/assets/alphaesletters/items", addonName), formatted));
        return true;
    }

    private static boolean generateBlockStateJson(String addonName) {
        readTemplateFromJar(BLOCK_STATE_TEMPLATE)
                .map(str -> String.format(str, addonName))
                .ifPresent(formatted ->
                        registerJson(formatPath("/assets/alphaesletters/blockstates", addonName), formatted));
        return true;
    }

    private static Addon extractModelFromJson(ObjectMapper mapper, Path file) {
        try {
            var addon = new Addon("custom_" + file.getFileName().toString(),
                    mapper.readValue(Files.readString(file), MinecraftModel.class));
            if (StringUtils.isEmpty(addon.model().getName())) {
                addon.model().setName(getAddonNameWithoutExtension(addon));
            }
            return addon;
        } catch (IOException ignored) {
            // Skip broken addon.
            return null;
        }
    }

    private static Optional<String> readTemplateFromJar(String path) {
        try (InputStream is = AddonResourceLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                return Optional.empty();
            }
            byte[] bytes = is.readAllBytes();
            return Optional.of(new String(bytes, StandardCharsets.UTF_8));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private static void registerJson(String path, String content) {
        GENERATED_PACK.addResource(path, content);
    }

    private static @NotNull String getAddonNameWithoutExtension(Addon addon) {
        return addon.addonName().replace(".json", "");
    }

    private static String formatPath(String path, String addonName) {
        return path + "/" + addonName + ".json";
    }

    private static LetterBasic registerAddonLetter(Addon addon) {
        String letterId = getAddonNameWithoutExtension(addon);
        var newBlock = new LetterBasic(letterId) {
            @Override
            public MutableText getName() {
                return Text.literal(addon.model().getName());
            }

            @Override
            public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip,
                                      TooltipType options) {
                tooltip.add(Text.literal(String.format(Text.translatable(AUTHOR_TOOLTIP).getString(),
                        addon.model().getAuthor())).formatted(Formatting.ITALIC));
                tooltip.add(Text.literal(String.format(Text.translatable(VERSION_TOOLTIP).getString(),
                                addon.model().getVersion()))
                        .formatted(Formatting.ITALIC));
            }
        };
        Registry.register(Registries.BLOCK, RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID,
                letterId)), newBlock);
        var newItem = new BlockItem(newBlock, new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, letterId)))) {
            @Override
            public Text getName(ItemStack stack) {
                return Text.of(addon.model().getName());
            }
        };
        Registry.register(Registries.ITEM, RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID,
                        letterId)),
                newItem);
        return newBlock;
    }

    public static List<Block> getAddonsBlocks() {
        return new ArrayList<>(ADDONS);
    }
}
