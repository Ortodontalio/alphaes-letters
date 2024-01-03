package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for (Block block : AlphaesUtils.getAllBlocks()) {
            addDrop(block);
        }
    }
}
