package com.ortodontalio.alphaesletters.common;

import com.ortodontalio.alphaesletters.tags.AlphaesTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags.DYES;

@SuppressWarnings("deprecation")
public class CroppedFerroconcrete extends Block implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.of("color", DyeColor.class, DyeColor.values());
    public static final EnumProperty<Letters> LETTER = EnumProperty.of("letter", Letters.class);

    public CroppedFerroconcrete() {
        super(FabricBlockSettings
                .of(Material.STONE, MapColor.BLUE)
                .strength(5.0f, 10.0f)
                .sounds(BlockSoundGroup.STONE)
                .luminance(state -> Boolean.TRUE.equals(state.get(LIT)) ? 10 : 0)
                .requiresTool());
        setDefaultState(getDefaultState()
                .with(LIT, false)
                .with(LETTER, Letters.NONE)
                .with(WATERLOGGED, false)
                .with(COLOR, DyeColor.WHITE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT, COLOR, WATERLOGGED, LETTER);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> Block.createCuboidShape(0, 0, 0, 16, 16, 15);
            case WEST -> Block.createCuboidShape(1, 0, 0, 16, 16, 16);
            case EAST -> Block.createCuboidShape(0, 0, 0, 15, 16, 16);
            default -> Block.createCuboidShape(0, 0, 1, 16, 16, 16);
        };
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack inHand = player.getStackInHand(hand);
        if (inHand.isOf(Items.GLOWSTONE_DUST) && Boolean.FALSE.equals(state.get(LIT))) {
            world.playSound(player, pos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.decrement(1);
            }
            world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL);
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(AlphaesTags.Items.AXES) && Boolean.TRUE.equals(state.get(LIT))) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.damage(1, player, p -> p.sendToolBreakStatus(hand));
            }
            world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_ALL);
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(AlphaesTags.Items.HOES) && !state.get(LETTER).equals(Letters.NONE)) {
            world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.damage(1, player, p -> p.sendToolBreakStatus(hand));
            }
            world.setBlockState(pos, state.with(LETTER, Letters.NONE), Block.NOTIFY_ALL);
            onBreak(world, pos, state, player);
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(AlphaesTags.Items.LETTERS) && state.get(LETTER).equals(Letters.NONE)) {
            Letters currentLetterInHand = Letters.findLetterByBlock(inHand);
            if (!state.get(LETTER).equals(currentLetterInHand)) {
                world.playSound(player, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    inHand.decrement(1);
                }
                world.setBlockState(pos, state.with(LETTER, currentLetterInHand), Block.NOTIFY_ALL);
                return ActionResult.SUCCESS;
            }
        }
        if (inHand.isIn(DYES) && !state.get(COLOR).equals(((DyeItem) inHand.getItem()).getColor())) {
            world.playSound(player, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, state.with(COLOR, ((DyeItem) inHand.getItem()).getColor()), Block.NOTIFY_ALL);
            if (!player.isCreative()) {
                inHand.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getPlayerFacing().getOpposite())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return Boolean.TRUE.equals(state.get(WATERLOGGED))
                ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (Boolean.TRUE.equals(state.get(WATERLOGGED))) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Letters currentLetter = state.get(LETTER);
        if (currentLetter != null) {
            ItemStack itemStack = new ItemStack(currentLetter.getBlock());
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    itemStack);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }
        super.onBreak(world, pos, state, player);
    }
}
