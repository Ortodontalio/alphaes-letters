package com.ortodontalio.alphaesletters.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.StateManager;

public class LetterBasic extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public LetterBasic() {
        super(FabricBlockSettings
                .of(Material.STONE)
                .strength(5.0f, 30.0f)
                .sounds(BlockSoundGroup.STONE)
                .requiresTool());
    }
    public LetterBasic(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING,ctx.getPlayerFacing().getOpposite());
    }
}
