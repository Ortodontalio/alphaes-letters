package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.letters.MiscLetters;
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
import net.minecraft.data.client.Models;
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

        stateGenerator.registerSimpleCubeAll(TechBlocks.LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.WHITE_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.ORANGE_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.MAGENTA_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.LIGHT_BLUE_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.YELLOW_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.LIME_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.PINK_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.GRAY_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.LIGHT_GRAY_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.CYAN_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.PURPLE_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.BROWN_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.GREEN_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.RED_LETTER_CONCRETE);
        stateGenerator.registerSimpleCubeAll(TechBlocks.BLACK_LETTER_CONCRETE);

        registerLetterPowderTexture(stateGenerator, TechBlocks.LETTER_POWDER, Blocks.WHITE_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.BLUE_LETTER_POWDER, Blocks.BLUE_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.ORANGE_LETTER_POWDER, Blocks.ORANGE_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.MAGENTA_LETTER_POWDER, Blocks.MAGENTA_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.LIGHT_BLUE_LETTER_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.YELLOW_LETTER_POWDER, Blocks.YELLOW_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.LIME_LETTER_POWDER, Blocks.LIME_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.PINK_LETTER_POWDER, Blocks.PINK_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.GRAY_LETTER_POWDER, Blocks.GRAY_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.LIGHT_GRAY_LETTER_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.CYAN_LETTER_POWDER, Blocks.CYAN_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.PURPLE_LETTER_POWDER, Blocks.PURPLE_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.BROWN_LETTER_POWDER, Blocks.BROWN_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.GREEN_LETTER_POWDER, Blocks.GREEN_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.RED_LETTER_POWDER, Blocks.RED_CONCRETE_POWDER);
        registerLetterPowderTexture(stateGenerator, TechBlocks.BLACK_LETTER_POWDER, Blocks.BLACK_CONCRETE_POWDER);

        stateGenerator.registerAxisRotated(TechBlocks.CONCRETE_WITH_BARS, TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);

    }

    public TextureMap sideAndEndForTopLetterPowder(Block block) {
        return new TextureMap()
                .put(TextureKey.SIDE, TextureMap.getId(block))
                .put(TextureKey.END, TextureMap.getSubId(block, "_up"))
                .put(TextureKey.PARTICLE, TextureMap.getId(block));
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

    private void registerDyeingMachineTexture(BlockStateModelGenerator stateGenerator, String frontTextureSuffix,
                                              String suffix) {
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

    private void registerLetterPowderTexture(BlockStateModelGenerator stateGenerator,
                                             Block letterPowder,
                                             Block relatedPowder) {
        var basicId = registerLetterPowderTexture(stateGenerator, Models.CUBE_COLUMN, letterPowder, relatedPowder);
        var horizId = registerLetterPowderTexture(stateGenerator, Models.CUBE_COLUMN_HORIZONTAL, letterPowder,
                relatedPowder);
        stateGenerator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(letterPowder,
                basicId, horizId));
    }

    private Identifier registerLetterPowderTexture(BlockStateModelGenerator stateGenerator,
                                                   Model model,
                                                   Block letterPowder,
                                                   Block relatedPowder) {
        Identifier top = TextureMap.getId(relatedPowder);
        Identifier end = TextureMap.getSubId(letterPowder, "_up");
        var texturedModel = TexturedModel.makeFactory(block -> new TextureMap()
                .put(TextureKey.END, end)
                .put(TextureKey.SIDE, top), model);
        return texturedModel.upload(letterPowder, stateGenerator.modelCollector);
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
        letterState.register(MiscLetters.NONE.asString(), BlockStateVariant.create().put(VariantSettings.MODEL,
                ModelIds.getBlockModelId(TechBlocks.CROPPED_LETTER_CONCRETE)));
        AlphaesUtils.getAllLettersNames().stream()
                .filter(letter -> !letter.equals(MiscLetters.NONE.asString()))
                .forEach(letter -> letterState.register(letter, BlockStateVariant.create().put(VariantSettings.MODEL,
                        Identifier.of(AlphaesLetters.MOD_ID, String.format("block/letter_block/%s", letter)))));
        return letterState;
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
