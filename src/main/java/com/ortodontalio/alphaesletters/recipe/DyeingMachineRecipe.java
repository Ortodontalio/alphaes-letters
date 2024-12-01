package com.ortodontalio.alphaesletters.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ortodontalio.alphaesletters.entity.DyeingMachineBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class DyeingMachineRecipe implements Recipe<DyeingMachineBlockEntity.DyeingMachineRecipeInput> {
    private final ItemStack output;
    private final List<Ingredient> ingredients;

    public DyeingMachineRecipe(List<Ingredient> ingredients, ItemStack output) {
        this.output = output;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(DyeingMachineBlockEntity.DyeingMachineRecipeInput inventory, World world) {
        if (world.isClient()) {
            return false;
        }
        return ingredients.get(0).test(inventory.getStackInSlot(1)) &&
                ingredients.get(1).test(inventory.getStackInSlot(2));
    }

    @Override
    public ItemStack craft(DyeingMachineBlockEntity.DyeingMachineRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }

    public ItemStack getResult() {
        return output;
    }

    @Override
    public RecipeSerializer<DyeingMachineRecipe> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<DyeingMachineRecipe> getType() {
        return Type.INSTANCE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forMultipleSlots(ingredients.stream().map(Optional::of).toList());
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> allIngredients = DefaultedList.ofSize(ingredients.size());
        allIngredients.addAll(ingredients);
        return allIngredients;
    }

    public static class Type implements RecipeType<DyeingMachineRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = "dyeing_machine";
    }

    public static class Serializer implements RecipeSerializer<DyeingMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        private static final String INGREDIENTS_FIELD = "ingredients";
        private static final String OUTPUT_FIELD = "output";
        public static final MapCodec<DyeingMachineRecipe> RECIPE_CODEC = RecordCodecBuilder
                .mapCodec(in -> in.group(
                        Ingredient.CODEC.listOf(1, 9).fieldOf(INGREDIENTS_FIELD).forGetter(DyeingMachineRecipe::getIngredients),
                        ItemStack.VALIDATED_CODEC.fieldOf(OUTPUT_FIELD).forGetter(DyeingMachineRecipe::getResult)
                ).apply(in, DyeingMachineRecipe::new));

        public static final PacketCodec<RegistryByteBuf, DyeingMachineRecipe> PACKET_CODEC = PacketCodec.tuple(
                Ingredient.PACKET_CODEC.collect(PacketCodecs.toList()),
                DyeingMachineRecipe::getIngredients,
                ItemStack.PACKET_CODEC,
                DyeingMachineRecipe::getResult,
                DyeingMachineRecipe::new
        );

        @Override
        public MapCodec<DyeingMachineRecipe> codec() {
            return RECIPE_CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DyeingMachineRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
