package com.ortodontalio.alphaesletters.generation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.tech.StrikethroughBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class LetterItemDefinitionProvider implements DataProvider {
    private final FabricDataOutput output;

    public LetterItemDefinitionProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (Block block : Registries.BLOCK) {
            if (!(block instanceof LetterBasic)) continue;
            Identifier id = Registries.BLOCK.getId(block);
            JsonObject json = getItemsJson(id);
            Path path = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "items")
                    .resolveJson(id);
            futures.add(DataProvider.writeToPath(writer, json, path));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private static @NotNull JsonObject getItemsJson(Identifier id) {
        JsonObject json = new JsonObject();

        JsonObject model = new JsonObject();
        model.addProperty("type", "minecraft:model");
        model.addProperty("model", id.getNamespace() + ":block/" + id.getPath());

        JsonArray tints = new JsonArray();
        JsonObject tint = new JsonObject();
        tint.addProperty("type", "alphaesletters:multi_color_tint");
        tints.add(tint);

        model.add("tints", tints);
        json.add("model", model);
        return json;
    }

    @Override
    public String getName() {
        return "Letter Item Definitions";
    }
}
