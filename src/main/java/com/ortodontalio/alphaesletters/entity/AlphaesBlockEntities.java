package com.ortodontalio.alphaesletters.entity;

import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesBlockEntities {
    public static BlockEntityType<DyeingMachineBlockEntity> DYEING_MACHINE_BLOCK_ENTITY;

    public static void registerEntities() {
        DYEING_MACHINE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MOD_ID, "dyeing_machine"),
                FabricBlockEntityTypeBuilder.create(DyeingMachineBlockEntity::new, TechBlocks.DYEING_MACHINE)
                        .build(null));
    }
}
