package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.tech.TechBlocks;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(TechBlocks.IRON_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(TechBlocks.IRON_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(AlphaesUtils.getAllSolidBlocks());
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(AlphaesUtils.getAllSolidBlocks());
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(TechBlocks.LETTER_POWDER);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(TechBlocks.STRIKETHROUGH_BLOCK);
    }
}
