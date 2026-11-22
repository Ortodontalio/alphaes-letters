package com.ortodontalio.alphaesletters.addon_system;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackInfo;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataSerializer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AddonResourcePack implements ResourcePack {
    private final Map<String, String> generatedResources = new HashMap<>();
    private final String packName;

    public AddonResourcePack(String packName) {
        this.packName = packName;
    }

    public void addResource(String path, String jsonContent) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        generatedResources.put(path, jsonContent);
    }

    @Override
    public void close() {}

    @Override
    public @Nullable InputSupplier<InputStream> openRoot(String... segments) {
        String fileName = String.join("/", segments);
        if ("pack.mcmeta".equals(fileName)) {
            String json = "{\"pack\":{\"pack_format\":61,\"description\":\"Discover your world of letters!\"}}";
            return () -> new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        }

        if ("pack.png".equals(fileName)) {
            return () -> {
                InputStream is = AddonResourcePack.class.getResourceAsStream("/assets/alphaesletters/pack.png");
                if (is == null) {
                    throw new IOException("pack.png not found in mod resources");
                }
                return is;
            };
        }
        return null;
    }

    @Override
    public @Nullable InputSupplier<InputStream> open(ResourceType type, Identifier id) {
        String dir = type.getDirectory();
        String path = dir + "/" + id.getNamespace() + "/" + id.getPath();
        String json = generatedResources.get(path);
        return json == null ? null : () -> new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void findResources(ResourceType type, String namespace, String prefix, ResultConsumer consumer) {
        String dir = type.getDirectory();
        String basePath = dir + "/" + namespace + "/";
        for (Map.Entry<String, String> entry : generatedResources.entrySet()) {
            String fullPath = entry.getKey();
            if (!fullPath.startsWith(basePath)) continue;
            String relative = fullPath.substring(basePath.length());
            if (prefix != null && !relative.startsWith(prefix)) continue;

            Identifier id = Identifier.of(namespace, relative);
            consumer.accept(id, () -> new ByteArrayInputStream(entry.getValue().getBytes(StandardCharsets.UTF_8)));
        }
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        String dir = type.getDirectory();
        return generatedResources.keySet().stream()
                .filter(path -> path.startsWith(dir + "/"))
                .map(path -> path.substring(dir.length() + 1).split("/")[0])
                .collect(Collectors.toSet());
    }

    @Override
    public @Nullable <T> T parseMetadata(ResourceMetadataSerializer<T> metadataSerializer) {
        InputSupplier<InputStream> supplier = openRoot("pack.mcmeta");
        if (supplier == null) return null;
        try (InputStream is = supplier.get()) {
            JsonObject json = JsonParser.parseString(new String(is.readAllBytes(), StandardCharsets.UTF_8))
                    .getAsJsonObject();
            if (json.has("pack")) {
                JsonObject packJson = json.getAsJsonObject("pack");
                return metadataSerializer.codec().parse(JsonOps.INSTANCE, packJson).result().orElse(null);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResourcePackInfo getInfo() {
        return new ResourcePackInfo(
                packName.trim(),
                Text.literal("OpenLetters Addons Resources"),
                ResourcePackSource.BUILTIN,
                Optional.empty()
        );
    }
}