package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyStateLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        AlphaesUtils.getAllTechBlocks().forEach(this::addDrop);
        AlphaesUtils.getAllLetterBlocks()
                .forEach(block -> addDrop(block, LootTable.builder().pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0f))
                                .with(ItemEntry.builder(block))
                                .conditionally(SurvivesExplosionLootCondition.builder())
                                .apply(CopyStateLootFunction.builder(block)
                                        .addProperty(LetterBasic.LIT)
                                        .addProperty(LetterBasic.COLOR)
                                )
                )));
        Stream.concat(AlphaesUtils.getAllLetterConcretesBlocks().stream(),
                        AlphaesUtils.getAllLetterConcretesWithBarsBlocks().stream())
                .forEach(this::addDropWithSilkTouch);
    }
}
