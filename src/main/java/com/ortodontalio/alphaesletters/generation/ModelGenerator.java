package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.tech.CroppedFerroconcrete;
import com.ortodontalio.alphaesletters.tech.DyeingMachine;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator stateGenerator) {
        BlockStateModelGenerator.BlockTexturePool fencePool = stateGenerator.registerCubeAllModelTexturePool(Blocks.IRON_BLOCK);
        fencePool.fence(TechBlocks.IRON_FENCE);
        fencePool.fenceGate(TechBlocks.IRON_FENCE_GATE);
        generateDyeingMachineBlockstate(stateGenerator);
        generateLettersBlockstates(stateGenerator);
        generateCroppedFerroconcreteBlockstate(stateGenerator);
    }

    private void generateCroppedFerroconcreteBlockstate(BlockStateModelGenerator stateGenerator) {
        stateGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(TechBlocks.CROPPED_LETTER_CONCRETE)
                .coordinate(createLetterPropertyMap())
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }

    private void generateLettersBlockstates(BlockStateModelGenerator stateGenerator) {
        AlphaesUtils.getAllLetterBlocks()
                .stream()
                .map(LetterBasic.class::cast)
                .forEach(letter -> stateGenerator.blockStateCollector.accept(
                        VariantsBlockStateSupplier.create(letter, BlockStateVariant.create().put(VariantSettings.MODEL,
                                        Identifier.of(AlphaesLetters.MOD_ID, String.format("block/%s", letter.getLetterName()))))
                                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())));
    }

    private void generateDyeingMachineBlockstate(BlockStateModelGenerator stateGenerator) {
        registerDyeingMachineTexture(stateGenerator, "_top_empty", null);
        registerDyeingMachineTexture(stateGenerator, "_top_water_half_down", "_watered_half_down");
        registerDyeingMachineTexture(stateGenerator, "_top_water_half", "_watered_half");
        registerDyeingMachineTexture(stateGenerator, "_top_water_half_up", "_watered_half_up");
        registerDyeingMachineTexture(stateGenerator, "_top_water", "_watered_full");
        var supplier = VariantsBlockStateSupplier.create(TechBlocks.DYEING_MACHINE)
                .coordinate(createWateredPropertyMap())
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates());
        stateGenerator.blockStateCollector.accept(supplier);
    }

    private void registerDyeingMachineTexture(BlockStateModelGenerator stateGenerator, String frontTextureSuffix, String suffix) {
        Identifier top = TextureMap.getSubId(TechBlocks.DYEING_MACHINE, "_up");
        Identifier side = TextureMap.getSubId(TechBlocks.DYEING_MACHINE, "_top");
        Identifier bottom = TextureMap.getSubId(TechBlocks.DYEING_MACHINE, "_bottom");
        Identifier front = TextureMap.getSubId(TechBlocks.DYEING_MACHINE, frontTextureSuffix);
        Identifier particle = TextureMap.getId(Blocks.IRON_BLOCK);
        var texturedModel = TexturedModel.makeFactory(this::sideFrontTopBottomWithParticle, orientableBlock())
                .get(TechBlocks.DYEING_MACHINE)
                .textures(textures -> textures
                        .put(TextureKey.FRONT, front)
                        .put(TextureKey.TOP, top)
                        .put(TextureKey.SIDE, side)
                        .put(TextureKey.BOTTOM, bottom)
                        .put(TextureKey.PARTICLE, particle));
        if (suffix != null && !suffix.isEmpty()) {
            texturedModel.upload(TechBlocks.DYEING_MACHINE, suffix, stateGenerator.modelCollector);
        } else {
            texturedModel.upload(TechBlocks.DYEING_MACHINE, stateGenerator.modelCollector);
        }
    }

    private Model orientableBlock() {
        return new Model(Optional.of(Identifier.ofVanilla("block/orientable")), Optional.empty(),
                TextureKey.PARTICLE, TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.FRONT);
    }

    private TextureMap sideFrontTopBottomWithParticle(Block block) {
        return new TextureMap()
                .put(TextureKey.PARTICLE, TextureMap.getId(block))
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_side"))
                .put(TextureKey.FRONT, TextureMap.getSubId(block, "_front"))
                .put(TextureKey.TOP, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
    }

    private BlockStateVariantMap createWateredPropertyMap() {
        return BlockStateVariantMap.create(DyeingMachine.WATERED)
                .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(TechBlocks.DYEING_MACHINE)))
                .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(TechBlocks.DYEING_MACHINE, "_watered_half_down")))
                .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(TechBlocks.DYEING_MACHINE, "_watered_half")))
                .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(TechBlocks.DYEING_MACHINE, "_watered_half_up")))
                .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(TechBlocks.DYEING_MACHINE, "_watered_full")));
    }

    private BlockStateVariantMap createLetterPropertyMap() {
        var letterState = BlockStateVariantMap.create(CroppedFerroconcrete.LETTER);
        for (String letter : AlphaesUtils.getAllLettersNames()) {
            letterState.register(letter, BlockStateVariant.create().put(VariantSettings.MODEL,
                    Identifier.of(AlphaesLetters.MOD_ID, String.format("block/%s", letter))));
        }
        return letterState;
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
