package com.ortodontalio.alphaesletters.entity;

import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesBlockEntities {
    public static BlockEntityType<DyeingMachineBlockEntity> dyeingMachineEntity;

    private AlphaesBlockEntities() {
    }

    public static void registerEntities() {
        dyeingMachineEntity = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                RegistryKey.of(RegistryKeys.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, "dyeing_machine")),
                FabricBlockEntityTypeBuilder.create(DyeingMachineBlockEntity::new, TechBlocks.DYEING_MACHINE)
                        .build(null));
    }
}
