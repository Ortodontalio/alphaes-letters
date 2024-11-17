package com.ortodontalio.alphaesletters.entity;

import com.ortodontalio.alphaesletters.tech.SymbolWallBlockEntity;
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
    public static final BlockEntityType<DyeingMachineBlockEntity> DYEING_MACHINE_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            RegistryKey.of(RegistryKeys.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, "dyeing_machine")),
            FabricBlockEntityTypeBuilder.create(DyeingMachineBlockEntity::new, TechBlocks.DYEING_MACHINE)
                    .build(null));
    public static final BlockEntityType<SymbolWallBlockEntity> SYMBOL_WALL_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            RegistryKey.of(RegistryKeys.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, "symbol_wall")),
            FabricBlockEntityTypeBuilder.create(SymbolWallBlockEntity::new, TechBlocks.SYMBOL_WALL)
                    .build(null));

    private AlphaesBlockEntities() {
    }

    public static void registerEntities() {
    }
}
