package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.tags.AlphaesTags;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(AlphaesTags.Items.CONCRETE_POWDER_BLOCKS)
                .add(AlphaesUtils.getAllConcretePowders());
        getOrCreateTagBuilder(AlphaesTags.Items.AXES)
                .add(Items.WOODEN_AXE)
                .add(Items.STONE_AXE)
                .add(Items.IRON_AXE)
                .add(Items.GOLDEN_AXE)
                .add(Items.DIAMOND_AXE)
                .add(Items.NETHERITE_AXE);
        getOrCreateTagBuilder(AlphaesTags.Items.FARBA_ITEMS)
                .add(AlphaesUtils.getAllDyes());
        getOrCreateTagBuilder(AlphaesTags.Items.HOES)
                .add(Items.WOODEN_HOE)
                .add(Items.STONE_HOE)
                .add(Items.IRON_HOE)
                .add(Items.GOLDEN_HOE)
                .add(Items.DIAMOND_HOE)
                .add(Items.NETHERITE_HOE);
        getOrCreateTagBuilder(AlphaesTags.Items.LETTERS)
                .add(AlphaesUtils.getAllLetterBlockItems());
    }
}
