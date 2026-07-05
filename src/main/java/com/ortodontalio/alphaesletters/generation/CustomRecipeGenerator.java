package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.recipe.DyeingMachineRecipeJsonBuilder;
import com.ortodontalio.alphaesletters.tags.AlphaesTags;
import com.ortodontalio.alphaesletters.tech.TechBlockItems;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class CustomRecipeGenerator extends FabricRecipeProvider {

    public CustomRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private void offerDyeingMachineRecipe(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter exporter, TagKey<Item> inputTag, Item inputDye, Item output) {
        DyeingMachineRecipeJsonBuilder.create(wrapperLookup.getOrThrow(RegistryKeys.ITEM), output, inputDye, inputTag).offerTo(exporter);
    }

    private void offerDyeingMachineRecipe(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter exporter, Item inputBlock, Item inputDye, Item output) {
        DyeingMachineRecipeJsonBuilder.create(wrapperLookup.getOrThrow(RegistryKeys.ITEM), output, inputDye, inputBlock).offerTo(exporter);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter exporter) {
        return new RecipeGenerator(wrapperLookup, exporter) {
            @Override
            public void generate() {
                // Recipes for concrete powders in dyeing machine
                for (int i = 0; i < AlphaesUtils.getAllDyes().length; i++) {
                    offerDyeingMachineRecipe(wrapperLookup, exporter, AlphaesTags.Items.CONCRETE_POWDER_BLOCKS,
                            AlphaesUtils.getAllDyes()[i], AlphaesUtils.getAllConcrete()[i]);
                }
                // Recipe for concrete with bars
                for (int i = 0; i < AlphaesUtils.getAllDyes().length; i++) {
                    offerDyeingMachineRecipe(wrapperLookup, exporter, AlphaesTags.Items.LETTER_POWDER_BLOCKS,
                            AlphaesUtils.getAllDyes()[i], AlphaesUtils.getAllConcretesWithBarsBlockItems()[i]);
                }
                // Recipe for letter concrete
                for (int i = 0; i < AlphaesUtils.getAllDyes().length; i++) {
                    var output = AlphaesUtils.getAllLetterConcretesBlockItems()[i];
                    var inputFarba = AlphaesUtils.getAllDyes()[i];
                    var inputConcrete = AlphaesUtils.getAllConcretesWithBarsBlockItems()[i];
                    createShapeless(RecipeCategory.DECORATIONS, output)
                            .input(inputFarba)
                            .input(inputConcrete)
                            .criterion(hasItem(inputFarba), conditionsFromItem(inputFarba))
                            .criterion(hasItem(inputConcrete), conditionsFromItem(inputConcrete))
                            .offerTo(exporter);
                }
                // Recipes for letters blocks
                for (BlockItem letterBlock : AlphaesUtils.getAllLetterBlockItems()) {
                    offerStonecuttingRecipe(RecipeCategory.DECORATIONS, letterBlock, Items.WHITE_CONCRETE);
                }
                // Recipe for iron fence
                createShaped(RecipeCategory.DECORATIONS, TechBlockItems.IRON_FENCE, 2)
                        .pattern("IBI")
                        .pattern("IBI")
                        .input('I', Items.IRON_INGOT)
                        .input('B', Items.IRON_BARS)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.IRON_BARS), conditionsFromItem(Items.IRON_BARS))
                        .offerTo(exporter);
                // Recipe for iron fence gate
                createShaped(RecipeCategory.DECORATIONS, TechBlockItems.IRON_FENCE_GATE, 2)
                        .pattern("IBI")
                        .pattern("I I")
                        .input('I', Items.IRON_INGOT)
                        .input('B', Items.IRON_BLOCK)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.IRON_BLOCK), conditionsFromItem(Items.IRON_BLOCK))
                        .offerTo(exporter);
                // Recipes from strikethrough block
                createShapeless(RecipeCategory.DECORATIONS, Items.ITEM_FRAME)
                        .input(TechBlockItems.STRIKETHROUGH_BLOCK)
                        .input(Items.LEATHER)
                        .criterion(hasItem(TechBlockItems.STRIKETHROUGH_BLOCK),
                                conditionsFromItem(TechBlockItems.STRIKETHROUGH_BLOCK))
                        .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.DECORATIONS, Items.PAINTING)
                        .input(TechBlockItems.STRIKETHROUGH_BLOCK)
                        .input(ItemTags.WOOL)
                        .criterion(hasItem(TechBlockItems.STRIKETHROUGH_BLOCK),
                                conditionsFromItem(TechBlockItems.STRIKETHROUGH_BLOCK))
                        .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                        .offerTo(exporter);
                // Recipe for letter powders
                for (int i = 0; i < AlphaesUtils.getAllDyes().length; i++) {
                    var output = AlphaesUtils.getAllLetterPowdersBlockItems()[i];
                    var input = AlphaesUtils.getAllConcretePowders()[i];
                    createShapeless(RecipeCategory.DECORATIONS, output)
                            .input(input)
                            .input(Items.IRON_BARS)
                            .criterion(hasItem(input), conditionsFromItem(input))
                            .criterion(hasItem(Items.IRON_BARS), conditionsFromItem(Items.IRON_BARS))
                            .offerTo(exporter);
                }
                // Recipe for dyeing machine
                createShaped(RecipeCategory.DECORATIONS, TechBlockItems.DYEING_MACHINE)
                        .pattern("IRI")
                        .pattern("GWB")
                        .pattern("IRI")
                        .input('W', Items.BUCKET)
                        .input('I', Items.IRON_INGOT)
                        .input('R', Items.REDSTONE_BLOCK)
                        .input('B', Items.BARREL)
                        .input('G', Items.GLASS)
                        .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.REDSTONE_BLOCK), conditionsFromItem(Items.REDSTONE_BLOCK))
                        .criterion(hasItem(Items.BARREL), conditionsFromItem(Items.BARREL))
                        .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "OpenLetters Recipes";
    }
}
