package com.ortodontalio.alphaesletters;

import com.ortodontalio.alphaesletters.generation.BlockTagGenerator;
import com.ortodontalio.alphaesletters.generation.ItemTagGenerator;
import com.ortodontalio.alphaesletters.generation.CustomRecipeGenerator;
import com.ortodontalio.alphaesletters.generation.LootTableGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AlphaesDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(BlockTagGenerator::new);
        pack.addProvider(ItemTagGenerator::new);
        pack.addProvider(CustomRecipeGenerator::new);
        pack.addProvider(LootTableGenerator::new);
    }
}
