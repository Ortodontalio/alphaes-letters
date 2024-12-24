package com.ortodontalio.alphaesletters.entity;

import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesBlockEntities {
    public static BlockEntityType<DyeingMachineBlockEntity> DYEING_MACHINE_BLOCK_ENTITY;

    private AlphaesBlockEntities() {
    }

    public static void registerEntities() {
        DYEING_MACHINE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MOD_ID, "dyeing_machine"),
                FabricBlockEntityTypeBuilder.create(DyeingMachineBlockEntity::new, TechBlocks.DYEING_MACHINE)
                        .build(null));
    }
}
