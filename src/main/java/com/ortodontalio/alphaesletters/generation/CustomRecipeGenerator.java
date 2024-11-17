package com.ortodontalio.alphaesletters.generation;

import com.ortodontalio.alphaesletters.AlphaesLetters;
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
                    offerDyeingMachineRecipe(wrapperLookup, exporter, AlphaesTags.Items.CONCRETE_POWDER_BLOCKS, AlphaesUtils.getAllDyes()[i],
                            AlphaesUtils.getAllConcrete()[i]);
                }
                // Recipe for letter concrete
                offerDyeingMachineRecipe(wrapperLookup, exporter, TechBlockItems.LETTER_POWDER, Items.BLUE_DYE, TechBlockItems.LETTER_CONCRETE);
                // Recipes for letters blocks
                for (BlockItem letterBlock : AlphaesUtils.getAllLetterBlockItems()) {
                    offerStonecuttingRecipe(RecipeCategory.DECORATIONS, letterBlock, Items.WHITE_CONCRETE);
                }
                // Recipe for cropped letter concrete
                offerStonecuttingRecipe(RecipeCategory.DECORATIONS, TechBlockItems.CROPPED_LETTER_CONCRETE,
                        TechBlockItems.LETTER_CONCRETE);
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
                // Recipe for strikethrough block
                createShaped(RecipeCategory.DECORATIONS, TechBlockItems.STRIKETHROUGH_BLOCK, 2)
                        .pattern("SSS")
                        .pattern("SRS")
                        .pattern("SSS")
                        .input('S', Items.STICK)
                        .input('R', Items.RED_DYE)
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .criterion(hasItem(Items.RED_DYE), conditionsFromItem(Items.RED_DYE))
                        .offerTo(exporter);
                // Recipe for concrete with bars
                createShapeless(RecipeCategory.DECORATIONS, TechBlockItems.LETTER_POWDER)
                        .input(Items.WHITE_CONCRETE_POWDER)
                        .input(Items.IRON_BARS)
                        .criterion(hasItem(Items.WHITE_CONCRETE_POWDER), conditionsFromItem(Items.WHITE_CONCRETE_POWDER))
                        .criterion(hasItem(Items.IRON_BARS), conditionsFromItem(Items.IRON_BARS))
                        .offerTo(exporter);
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
        return "AlPhAE's Letters Recipes";
    }
}
