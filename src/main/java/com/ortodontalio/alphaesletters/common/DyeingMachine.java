package com.ortodontalio.alphaesletters.common;


import com.mojang.serialization.MapCodec;
import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.entity.AlphaesBlockEntities;
import com.ortodontalio.alphaesletters.entity.DyeingMachineBlockEntity;
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
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class DyeingMachine extends BlockWithEntity implements BlockEntityProvider {

    public static final IntProperty WATERED = IntProperty.of("watered", 0, 4);
    public static final MapCodec<DyeingMachine> CODEC = AbstractBlock.createCodec(sets -> new DyeingMachine());
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final String ID = "dyeing_machine";

    public DyeingMachine() {
        super(Settings
                .create()
                .strength(4.0f, 6.0f)
                .sounds(BlockSoundGroup.METAL)
                .requiresTool()
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, ID))));
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
            if (blockEntity instanceof DyeingMachineBlockEntity dmEntity) {
                ItemScatterer.spawn(world, pos, dmEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack itemInHand, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            DyeingMachineBlockEntity currentEntity = (DyeingMachineBlockEntity) world.getBlockEntity(pos);
            if (itemInHand.getItem().equals(Items.WATER_BUCKET) && currentEntity != null) {
                currentEntity.fillWater(world, state, pos);
                player.setStackInHand(hand, Items.BUCKET.getDefaultStack());
                currentEntity.markDirty();
            } else if (itemInHand.getItem().equals(Items.BUCKET) && currentEntity != null &&
                    DyeingMachineBlockEntity.isFullyWatered(currentEntity)) {
                currentEntity.emptyWater(world, state, pos);
                player.setStackInHand(hand, Items.WATER_BUCKET.getDefaultStack());
                currentEntity.markDirty();
            } else {
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
                if (screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeingMachineBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world instanceof ServerWorld serverWorld
                ? validateTicker(type, AlphaesBlockEntities.DYEING_MACHINE_ENTITY, (worldx, pos, statex, blockEntity) -> DyeingMachineBlockEntity.tick(serverWorld, pos, statex, blockEntity))
                : null;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (Boolean.TRUE.equals(state.get(LIT))) {
            double d = pos.getX() + 0.5;
            double e = pos.getY();
            double f = pos.getZ() + 0.5;
            if (Math.abs(random.nextDouble() - 10e-2) < 10e-2) {
                world.playSound(d, e, f, SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS, SoundCategory.BLOCKS, 0.4F, 0.5F, true);
            }
            Direction[] directions = Direction.values();
            for (Direction direction : directions) {
                d = 0.5625;
                Direction.Axis axis = direction.getAxis();
                e = axis == Direction.Axis.X ? 0.5 + d * direction.getOffsetX() : (double) random.nextFloat();
                f = axis == Direction.Axis.Y ? 0.5 + d * direction.getOffsetY() : (double) random.nextFloat();
                double g = axis == Direction.Axis.Z ? 0.5 + d * direction.getOffsetZ() : (double) random.nextFloat();
                world.addParticle(ParticleTypes.SMOKE, pos.getX() + e, pos.getY() + f, pos.getZ() + g,
                        0.0, 0.0, 0.0);
            }
        }
    }
}
