package com.ortodontalio.alphaesletters.recipe;

import com.ortodontalio.alphaesletters.tech.DyeingMachine;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryKey.of(RegistryKeys.RECIPE_SERIALIZER, Identifier.of(MOD_ID, DyeingMachine.ID)),
                DyeingMachineRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, RegistryKey.of(RegistryKeys.RECIPE_TYPE, Identifier.of(MOD_ID, DyeingMachineRecipe.Type.ID)),
                DyeingMachineRecipe.Type.INSTANCE);
    }
}
