package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.common.DyeingMachine;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.When;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator stateGenerator) {
        BlockStateModelGenerator.BlockTexturePool fencePool = stateGenerator.registerCubeAllModelTexturePool(Blocks.IRON_BLOCK);
        fencePool.fence(TechBlocks.IRON_FENCE);
        fencePool.fenceGate(TechBlocks.IRON_FENCE_GATE);
        stateGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(TechBlocks.DYEING_MACHINE)
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(DyeingMachine.WATERED, 0),
                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(DyeingMachine.WATERED, 1),
                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(DyeingMachine.WATERED, 2),
                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0))
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
