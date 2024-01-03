package com.ortodontalio.alphaesletters.recipe;

import com.ortodontalio.alphaesletters.common.DyeingMachine;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, DyeingMachine.ID),
                DyeingMachineRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(MOD_ID, DyeingMachineRecipe.Type.ID),
                DyeingMachineRecipe.Type.INSTANCE);
    }
}
