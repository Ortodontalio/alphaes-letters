package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
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
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.client.When;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator stateGenerator) {
        Identifier postModel = ModelIds.getBlockModelId(TechBlocks.IRON_FENCE)
                .withSuffixedPath("_post");

        Identifier sideModel = ModelIds.getBlockModelId(TechBlocks.IRON_FENCE)
                .withSuffixedPath("_side");

        stateGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createFenceBlockState(
                        TechBlocks.IRON_FENCE,
                        postModel,
                        sideModel
                )
        );

        Identifier gateModel = ModelIds.getBlockModelId(TechBlocks.IRON_FENCE_GATE);
        Identifier gateOpenModel = ModelIds.getBlockModelId(TechBlocks.IRON_FENCE_GATE)
                .withSuffixedPath("_open");
        Identifier gateWallModel = ModelIds.getBlockModelId(TechBlocks.IRON_FENCE_GATE)
                .withSuffixedPath("_wall");
        Identifier gateWallOpenModel = ModelIds.getBlockModelId(TechBlocks.IRON_FENCE_GATE)
                .withSuffixedPath("_wall_open");

        stateGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createFenceGateBlockState(
                        TechBlocks.IRON_FENCE_GATE,
                        gateOpenModel,
                        gateModel,
                        gateWallOpenModel,
                        gateWallModel,
                        false
                )
        );

        generateDyeingMachineBlockstate(stateGenerator);
        generateLettersBlockstates(stateGenerator);

        AlphaesUtils.getAllLetterConcretesBlocks().forEach(stateGenerator::registerSimpleCubeAll);
        AlphaesUtils.getAllExfoliatedConcreteBlocks().forEach(stateGenerator::registerSimpleCubeAll);

        var concretes = AlphaesUtils.getAllConcreteBlocks();
        var barsConcretes = AlphaesUtils.getAllLetterConcretesWithBarsBlocks();
        for (int i = 0; i < concretes.size(); i++) {
            registerLetterConcreteTexture(stateGenerator, barsConcretes.get(i), concretes.get(i));
        }

        var concretePowders = AlphaesUtils.getAllConcretePowdersBlocks();
        var letterPowders = AlphaesUtils.getAllLetterPowdersBlocks();
        for (int i = 0; i < concretePowders.size(); i++) {
            registerLetterConcreteTexture(stateGenerator, letterPowders.get(i), concretePowders.get(i));
        }
    }

    private void generateLettersBlockstates(BlockStateModelGenerator stateGenerator) {
        AlphaesUtils.getAllLetterBlocks()
                .stream()
                .map(LetterBasic.class::cast)
                .forEach(letter -> generateMultipartLetter(stateGenerator, letter));
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

    private void registerLetterConcreteTexture(BlockStateModelGenerator stateGenerator,
                                               Block letterPowder,
                                               Block relatedPowder) {
        var basicId = registerLetterConcreteTexture(stateGenerator, Models.CUBE_COLUMN, letterPowder, relatedPowder);
        var horizId = registerLetterConcreteTexture(stateGenerator, Models.CUBE_COLUMN_HORIZONTAL, letterPowder,
                relatedPowder);
        stateGenerator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(letterPowder,
                basicId, horizId));
    }

    private Identifier registerLetterConcreteTexture(BlockStateModelGenerator stateGenerator,
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

    private void generateMultipartLetter(BlockStateModelGenerator stateGenerator, LetterBasic letter) {
        Identifier letterModel = Identifier.of(
                AlphaesLetters.MOD_ID,
                "block/" + letter.getLetterName()
        );
        Identifier frameModel = Identifier.of(
                AlphaesLetters.MOD_ID,
                "block/strikethrough_block"
        );

        MultipartBlockStateSupplier supplier =
                MultipartBlockStateSupplier.create(letter);

        supplier.with(
                When.create()
                        .set(LetterBasic.FACING, Direction.NORTH),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, letterModel)
        );

        supplier.with(
                When.create()
                        .set(LetterBasic.FACING, Direction.SOUTH),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, letterModel)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180)
        );

        supplier.with(
                When.create()
                        .set(LetterBasic.FACING, Direction.EAST),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, letterModel)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90)
        );

        supplier.with(
                When.create()
                        .set(LetterBasic.FACING, Direction.WEST),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, letterModel)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270)
        );

        supplier.with(
                When.create()
                        .set(LetterBasic.FACING, Direction.NORTH)
                        .set(LetterBasic.HAS_COVER, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, frameModel)
        );

        supplier.with(
                When.create()
                        .set(LetterBasic.FACING, Direction.SOUTH)
                        .set(LetterBasic.HAS_COVER, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, frameModel)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180)
        );

        supplier.with(
                When.create()
                        .set(LetterBasic.FACING, Direction.EAST)
                        .set(LetterBasic.HAS_COVER, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, frameModel)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90)
        );

        supplier.with(
                When.create()
                        .set(LetterBasic.FACING, Direction.WEST)
                        .set(LetterBasic.HAS_COVER, true),
                BlockStateVariant.create()
                        .put(VariantSettings.MODEL, frameModel)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270)
        );

        stateGenerator.blockStateCollector.accept(supplier);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
