package com.ortodontalio.alphaesletters.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesRecipes {
    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, DyeingMachineRecipe.Serializer.ID),
                DyeingMachineRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, DyeingMachineRecipe.Type.ID),
                DyeingMachineRecipe.Type.INSTANCE);
    }
}
