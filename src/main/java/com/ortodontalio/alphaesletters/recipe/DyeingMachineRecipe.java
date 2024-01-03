package com.ortodontalio.alphaesletters.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public class DyeingMachineRecipe implements Recipe<SimpleInventory> {
    private final ItemStack output;
    private final List<Ingredient> ingredients;

    public DyeingMachineRecipe(List<Ingredient> ingredients, ItemStack output) {
        this.output = output;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }
        if (ingredients.get(0).test(inventory.getStack(1))) {
            return ingredients.get(1).test(inventory.getStack(2));
        }
        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
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
        public static final Codec<DyeingMachineRecipe> RECIPE_CODEC = RecordCodecBuilder.create(in ->
                in.group(validate().fieldOf(INGREDIENTS_FIELD).forGetter(DyeingMachineRecipe::getIngredients),
                        ItemStack.RECIPE_RESULT_CODEC.fieldOf(OUTPUT_FIELD).forGetter(r -> r.output)
                ).apply(in, DyeingMachineRecipe::new));

        private static Codec<List<Ingredient>> validate() {
            return Codecs.validate(Ingredient.DISALLOW_EMPTY_CODEC.listOf(), Serializer::validateIngredients);
        }

        private static DataResult<List<Ingredient>> validateIngredients(List<Ingredient> ingredients) {
            if (ingredients == null || ingredients.isEmpty()) {
                return DataResult.error(() -> "Recipe has no ingredients!");
            }
            if (ingredients.size() > 9) {
                return DataResult.error(() -> "Recipe has too many ingredients!");
            }
            return DataResult.success(ingredients);
        }

        @Override
        public void write(PacketByteBuf buf, DyeingMachineRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.getResult(null));
        }

        @Override
        public Codec<DyeingMachineRecipe> codec() {
            return RECIPE_CODEC;
        }

        @Override
        public DyeingMachineRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));
            ItemStack output = buf.readItemStack();
            return new DyeingMachineRecipe(inputs, output);
        }
    }
}
