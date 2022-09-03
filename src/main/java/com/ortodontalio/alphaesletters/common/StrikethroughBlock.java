package com.ortodontalio.alphaesletters.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class StrikethroughBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public StrikethroughBlock() {
        super(FabricBlockSettings
                .of(Material.WOOD)
                .strength(1.0f, 1.0f)
                .sounds(BlockSoundGroup.WOOD)
                .nonOpaque());
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch(state.get(FACING)) {
            case SOUTH -> Block.createCuboidShape(0,0,0,16,16,1);
            case WEST -> Block.createCuboidShape(15,0,0,16,16,16);
            case EAST -> Block.createCuboidShape(0,0,0,1,16,16);
            default -> Block.createCuboidShape(0,0,15,16,16,16);
        };
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
