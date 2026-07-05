package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        Arrays.stream(AlphaesUtils.getAllBlocks())
                .forEach(this::addDrop);
        Stream.concat(AlphaesUtils.getAllLetterConcretesBlocks().stream(),
                        AlphaesUtils.getAllLetterConcretesWithBarsBlocks().stream())
                .forEach(this::addDropWithSilkTouch);
    }
}
