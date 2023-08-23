package com.ortodontalio.alphaesletters.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import static com.ortodontalio.alphaesletters.tech.TechBlocks.CONCRETE_WITH_BARS;

@SuppressWarnings("deprecation")
public class LetterPowder extends PillarBlock {
    private final BlockState hardenedState;

    public LetterPowder() {
        super(FabricBlockSettings
                .of(Material.SOIL, MapColor.WHITE_GRAY)
                .strength(2.0f, 1.0f)
                .sounds(BlockSoundGroup.SAND));
        this.hardenedState = CONCRETE_WITH_BARS.getDefaultState();
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return hardensOnAnySide(world, pos) ? this.hardenedState : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private static boolean hardensOnAnySide(BlockView world, BlockPos pos) {
        boolean bl = false;
        BlockPos.Mutable mutable = pos.mutableCopy();
        Direction[] var4 = Direction.values();
        for (Direction direction : var4) {
            BlockState blockState = world.getBlockState(mutable);
            if (direction != Direction.DOWN || hardensIn(blockState)) {
                mutable.set(pos, direction);
                blockState = world.getBlockState(mutable);
                if (hardensIn(blockState) && !blockState.isSideSolidFullSquare(world, pos, direction.getOpposite())) {
                    bl = true;
                    break;
                }
            }
        }
        return bl;
    }

    private static boolean hardensIn(BlockState state) {
        return state.getFluidState().isIn(FluidTags.WATER);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player,
                           BlockPos pos, BlockState state,
                           @Nullable BlockEntity blockEntity,
                           ItemStack stack) {
        ItemStack simplePowder = Blocks.WHITE_CONCRETE_POWDER.asItem().getDefaultStack();
        BlockState simplePowderState = Blocks.WHITE_CONCRETE_POWDER.getDefaultState();
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        dropStacks(simplePowderState, world, pos, blockEntity, player, simplePowder);
        BlockState bars = Blocks.IRON_BARS.getDefaultState();
        world.setBlockState(pos, bars, Block.NOTIFY_ALL);
    }
}
