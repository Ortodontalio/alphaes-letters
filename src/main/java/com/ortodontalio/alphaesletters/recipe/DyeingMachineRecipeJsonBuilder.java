package com.ortodontalio.alphaesletters.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class DyeingMachineRecipeJsonBuilder implements CraftingRecipeJsonBuilder {

    private static final String RECIPE_PATH = String.format("recipes/%s/", RecipeCategory.DECORATIONS.getName());
    private static final String BLOCK_TAG_NULL_ERR = "Both the input block and the tag are null";
    private final Item output;
    private final Item inputDye;
    private Item inputBlock;
    private TagKey<Item> inputTag;
    private final Map<String, CriterionConditions> criteria = new LinkedHashMap<>();

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
    public CraftingRecipeJsonBuilder criterion(String name, CriterionConditions conditions) {
        this.criteria.put(name, conditions);
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
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        Advancement.Builder builder = Advancement.Builder.create()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(CriterionMerger.OR);
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
        exporter.accept(new JsonBuilder(recipeId, output, ingredients, builder, new Identifier(recipeId.getNamespace(),
                "recipes/" + recipeId.getPath())));
    }

    public static class JsonBuilder implements RecipeJsonProvider {
        private final Identifier id;
        private final Item result;
        private final List<Ingredient> ingredients;
        private final Advancement.Builder advancement;
        private final Identifier advancementId;

        public JsonBuilder(Identifier id, Item result, List<Ingredient> ingredients,
                           Advancement.Builder advancement, Identifier advancementId) {
            this.id = id;
            this.result = result;
            this.ingredients = ingredients;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serialize(JsonObject json) {
            JsonArray ingArray = new JsonArray();
            ingredients.stream().map(Ingredient::toJson).forEach(ingArray::add);
            json.add("ingredients", ingArray);
            JsonObject outputObj = new JsonObject();
            outputObj.addProperty("item", Registries.ITEM.getId(result).toString());
            json.add("output", outputObj);
        }

        @Override
        public Identifier getRecipeId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return DyeingMachineRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject toAdvancementJson() {
            return advancement.toJson();
        }

        @Nullable
        @Override
        public Identifier getAdvancementId() {
            return advancementId;
        }
    }
}
