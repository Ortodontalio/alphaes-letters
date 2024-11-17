package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.entity.AlphaesBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.math.BlockPos;

public class SymbolWallBlockEntity extends SignBlockEntity {
    public SymbolWallBlockEntity(BlockPos pos, BlockState state) {
        super(AlphaesBlockEntities.SYMBOL_WALL_ENTITY, pos, state);
    }
}
