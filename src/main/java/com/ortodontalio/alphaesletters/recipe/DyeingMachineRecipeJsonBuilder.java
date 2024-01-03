package com.ortodontalio.alphaesletters.recipe;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DyeingMachineRecipeJsonBuilder implements CraftingRecipeJsonBuilder {

    private static final String RECIPE_PATH = String.format("recipes/%s/", RecipeCategory.DECORATIONS.getName());
    private static final String BLOCK_TAG_NULL_ERR = "Both the input block and the tag are null";
    private final Item output;
    private final Item inputDye;
    private Item inputBlock;
    private TagKey<Item> inputTag;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();

    public static DyeingMachineRecipeJsonBuilder create(Item output, Item inputDye, Item inputBlock) {
        return new DyeingMachineRecipeJsonBuilder(output, inputDye, inputBlock);
    }

    public static DyeingMachineRecipeJsonBuilder create(Item output, Item inputDye, TagKey<Item> inputTag) {
        return new DyeingMachineRecipeJsonBuilder(output, inputDye, inputTag);
    }

    private DyeingMachineRecipeJsonBuilder(Item output, Item inputDye, Item inputBlock) {
        this.output = output;
        this.inputDye = inputDye;
        this.inputBlock = inputBlock;
    }

    private DyeingMachineRecipeJsonBuilder(Item output, Item inputDye, TagKey<Item> inputTag) {
        this.output = output;
        this.inputDye = inputDye;
        this.inputTag = inputTag;
    }

    @Override
    public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return output;
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        Objects.requireNonNull(builder);
        criteria.forEach(builder::criterion);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(Ingredient.ofItems(inputDye));
        if (inputBlock != null) {
            ingredients.add(0, Ingredient.ofItems(inputBlock));
        } else if (inputTag != null) {
            ingredients.add(0, Ingredient.fromTag(inputTag));
        } else {
            throw new IllegalArgumentException(BLOCK_TAG_NULL_ERR);
        }
        DyeingMachineRecipe dyeingMachineRecipe = new DyeingMachineRecipe(ingredients, output.getDefaultStack());
        exporter.accept(recipeId, dyeingMachineRecipe, builder.build(recipeId.withPrefixedPath(RECIPE_PATH)));
    }
}
