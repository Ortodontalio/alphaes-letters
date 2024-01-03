package com.ortodontalio.alphaesletters.common;

import com.mojang.serialization.MapCodec;
import com.ortodontalio.alphaesletters.entity.AlphaesBlockEntities;
import com.ortodontalio.alphaesletters.entity.DyeingMachineBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class DyeingMachine extends BlockWithEntity implements BlockEntityProvider {

    public static final IntProperty WATERED = IntProperty.of("watered", 0, 2);
    public static final MapCodec<DyeingMachine> CODEC = AbstractBlock.createCodec(sets -> new DyeingMachine());
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final String ID = "dyeing_machine";

    public DyeingMachine() {
        super(FabricBlockSettings
                .create()
                .strength(4.0f, 6.0f)
                .sounds(BlockSoundGroup.METAL)
                .requiresTool());
        setDefaultState(getDefaultState().with(WATERED, 0).with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERED, FACING, LIT);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DyeingMachineBlockEntity) {
                ItemScatterer.spawn(world, pos, (DyeingMachineBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            ItemStack itemInHand = player.getStackInHand(hand);
            DyeingMachineBlockEntity currentEntity = (DyeingMachineBlockEntity) world.getBlockEntity(pos);
            if (itemInHand.getItem().equals(Items.WATER_BUCKET) && currentEntity != null) {
                currentEntity.fillWater(world, state, pos);
                player.setStackInHand(hand, Items.BUCKET.getDefaultStack());
            } else if (itemInHand.getItem().equals(Items.BUCKET) && currentEntity != null &&
                    DyeingMachineBlockEntity.isFullyWatered(currentEntity)) {
                currentEntity.emptyWater(world, state, pos);
                player.setStackInHand(hand, Items.WATER_BUCKET.getDefaultStack());
            } else {
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
                if (screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeingMachineBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, AlphaesBlockEntities.dyeingMachineEntity, DyeingMachineBlockEntity::tick);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (Boolean.TRUE.equals(state.get(LIT))) {
            double d = 0.5625;
            Direction[] var5 = Direction.values();
            for (Direction direction : var5) {
                Direction.Axis axis = direction.getAxis();
                double e = axis == Direction.Axis.X ? 0.5 + d * direction.getOffsetX() : (double) random.nextFloat();
                double f = axis == Direction.Axis.Y ? 0.5 + d * direction.getOffsetY() : (double) random.nextFloat();
                double g = axis == Direction.Axis.Z ? 0.5 + d * direction.getOffsetZ() : (double) random.nextFloat();
                world.addParticle(ParticleTypes.SMOKE, pos.getX() + e, pos.getY() + f, pos.getZ() + g,
                        0.0, 0.0, 0.0);
            }
        }
    }
}
