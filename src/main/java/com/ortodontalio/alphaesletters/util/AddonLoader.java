package com.ortodontalio.alphaesletters.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.codegen.GroupRegistrator;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.util.models.Addon;
import com.ortodontalio.alphaesletters.util.models.MinecraftModel;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class AddonLoader {

    public static void checkAddonFolder() {
        var addonsPath = new File("./openletters");
        addonsPath.mkdir();
        try (Stream<Path> addons = Files.walk(Paths.get(addonsPath.getPath()))) {
            ObjectMapper mapper = JsonMapper.builder()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                    .build();
            GroupRegistrator.registerGroup("CustomLetters", addons
                    .filter(addon -> addon.getFileName().toString().endsWith(".json"))
                    .map(addon -> extractModelFromJson(mapper, addon))
                    .peek(addon -> generateJsonFromModel(mapper, addon))
                    .map(addon -> registerAddonLetter(addon.addonName().replace(".json", "")))
                    .toList());
        } catch (IOException ignored) {
        }
    }

    private static Addon extractModelFromJson(ObjectMapper mapper, Path file) {
        try {
            return new Addon(file.getFileName().toString(),
                    mapper.readValue(Files.readString(file), MinecraftModel.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateJsonFromModel(ObjectMapper mapper, Addon addon) {
        try {
            var resources = AddonLoader.class.getResource("/assets/alphaesletters/models/block").toURI();
            var outputFile = Paths.get(Paths.get(resources).toString(), addon.addonName()).toFile();
            outputFile.createNewFile();
            Files.writeString(outputFile.toPath(), mapper.writeValueAsString(addon.model()).toLowerCase(),
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static BlockItem registerAddonLetter(String letterId) {
        var newBlock = new LetterBasic(letterId);
        Registry.register(Registries.BLOCK, RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID,
                letterId)), newBlock);
        var newItem = new BlockItem(newBlock, new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM,
                        Identifier.of(AlphaesLetters.MOD_ID, letterId))));
        Registry.register(Registries.ITEM, RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AlphaesLetters.MOD_ID, letterId)),
                newItem);
        return newItem;
    }
}
