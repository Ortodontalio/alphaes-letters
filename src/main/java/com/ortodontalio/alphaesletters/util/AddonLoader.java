package com.ortodontalio.alphaesletters.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.stream.JsonReader;
import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.codegen.GroupRegistrator;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.util.models.Addon;
import com.ortodontalio.alphaesletters.util.models.MinecraftModel;
import com.ortodontalio.alphaesletters.util.models.TagModel;
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
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddonLoader {

    private static final String ITEM_MODEL_TEMPLATE = """
            {
              "model": {
                "type": "minecraft:model",
                "model": "alphaesletters:block/%1$s",
                "tints": [
                  {
                    "type": "alphaesletters:multi_color_tint"
                  }
                ]
              }
            }
            """;
    private static final String BLOCK_STATE_TEMPLATE = "mod_data/blockstates_template.json";
    private static final String LOOT_TABLE_TEMPLATE = "mod_data/loot_table_template.json";
    private static final String RECIPE_DEF_TEMPLATE = "mod_data/recipe_def_template.json";
    private static final String RECIPE_CONF_TEMPLATE = "mod_data/recipe_conf_template.json";
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
                        if (generateBlockJsonFromModel(mapper, addon) &&
                                generateItemJson(addon) &&
                                generateBlockStateJson(addon) &&
                                generateLootTableJson(addon) &&
                                generateRecipeDefJson(addon) &&
                                generateRecipeConfJson(addon)) {
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
            appendBlocksToTagFromJson(mapper, "/data/minecraft/tags/block/mineable/pickaxe.json", ADDONS);
            appendBlocksToTagFromJson(mapper, "/data/minecraft/tags/block/needs_iron_tool.json", ADDONS);
        } catch (IOException ignored) {
        }
    }

    public static List<Block> getAddonsBlocks() {
        return new ArrayList<>(ADDONS);
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

    private static boolean appendBlocksToTagFromJson(ObjectMapper mapper, String path, List<Block> addons) {
        try {
            var resources = AddonLoader.class.getResource(path).toURI();
            var file = Path.of(resources);
            var tagInfo = mapper.readValue(Files.readString(file), TagModel.class);
            tagInfo.addValues(addons.stream()
                    .map(ad -> Registries.BLOCK.getId(ad).toString())
                    .collect(Collectors.toSet()));
            Files.writeString(file, mapper.writeValueAsString(tagInfo), StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException | URISyntaxException ignored) {
            // Skip broken addons.
            return false;
        }
    }

    private static boolean generateBlockJsonFromModel(ObjectMapper mapper, Addon addon) {
        try {
            return generateJson(addon, "/assets/alphaesletters/models/block",
                    mapper.writeValueAsString(addon.model()).toLowerCase());
        } catch (IOException ignored) {
            // Skip broken addon.
            return false;
        }
    }

    private static boolean generateItemJson(Addon addon) {
        return generateJson(addon, "/assets/alphaesletters/items", String.format(ITEM_MODEL_TEMPLATE,
                getAddonNameWithoutExtension(addon)));
    }

    private static boolean generateBlockStateJson(Addon addon) {
        return generateJson(addon, "/assets/alphaesletters/blockstates", String.format(Objects
                        .requireNonNull(readJsonFromResources(BLOCK_STATE_TEMPLATE)),
                getAddonNameWithoutExtension(addon)));
    }

    private static boolean generateLootTableJson(Addon addon) {
        return generateJson(addon, "/data/alphaesletters/loot_table/blocks", String.format(Objects
                        .requireNonNull(readJsonFromResources(LOOT_TABLE_TEMPLATE)),
                getAddonNameWithoutExtension(addon)));
    }

    private static boolean generateRecipeDefJson(Addon addon) {
        return generateJson(addon, "/data/alphaesletters/recipe", String.format(Objects
                        .requireNonNull(readJsonFromResources(RECIPE_DEF_TEMPLATE)),
                getAddonNameWithoutExtension(addon)));
    }

    private static boolean generateRecipeConfJson(Addon addon) {
        return generateJson(addon, "/data/alphaesletters/advancement/recipes/decorations", String.format(Objects
                        .requireNonNull(readJsonFromResources(RECIPE_CONF_TEMPLATE)),
                getAddonNameWithoutExtension(addon)));
    }

    private static String readJsonFromResources(String fileName) {
        try (InputStream inputStream = JsonReader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {
            return IOUtils.toString(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
        } catch (Exception e) {
        }
        // Impossible to reach null
        return null;
    }

    private static boolean generateJson(Addon addon, String path, String content) {
        try {
            var resources = AddonLoader.class.getResource(path).toURI();
            var outputFile = Paths.get(Paths.get(resources).toString(), addon.addonName()).toFile();
            outputFile.createNewFile();
            Files.writeString(outputFile.toPath(), content, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException | URISyntaxException ignored) {
            // Skip broken addon.
            return false;
        }
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
        Registry.register(Registries.ITEM, RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, letterId)),
                newItem);
        return newBlock;
    }

    private static @NotNull String getAddonNameWithoutExtension(Addon addon) {
        return addon.addonName().replace(".json", "");
    }
}
