package com.ortodontalio.alphaesletters.common;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.tags.AlphaesTags;
import com.ortodontalio.alphaesletters.tech.StrikethroughBlock;
import com.ortodontalio.alphaesletters.tech.TechBlockItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

import static net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags.DYES;

public class LetterBasic extends Block implements Waterloggable, HasColor {

    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty HAS_COVER = BooleanProperty.of("has_cover");
    public static final EnumProperty<DyeColor> COVER_COLOR = EnumProperty.of("cover_color", DyeColor.class);
    public final String name;

    public LetterBasic(String name) {
        super(Settings
                .create()
                .mapColor(MapColor.WHITE)
                .strength(4.0f, 10.0f)
                .sounds(BlockSoundGroup.STONE)
                .luminance(state -> Boolean.TRUE.equals(state.get(LIT)) ? 10 : 0)
                .nonOpaque()
                .requiresTool()
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, name))));
        setDefaultState(getDefaultState()
                .with(LIT, false)
                .with(WATERLOGGED, false)
                .with(COLOR, DyeColor.WHITE)
                .with(HAS_COVER, false)
                .with(COVER_COLOR, DyeColor.WHITE));
        this.name = name;
    }

    public String getLetterName() {
        return name;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT, WATERLOGGED, COLOR, HAS_COVER, COVER_COLOR);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> Block.createCuboidShape(0, 0, 0, 16, 16, 2);
            case WEST -> Block.createCuboidShape(14, 0, 0, 16, 16, 16);
            case EAST -> Block.createCuboidShape(0, 0, 0, 2, 16, 16);
            default -> Block.createCuboidShape(0, 0, 14, 16, 16, 16);
        };
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        ItemStack copied = new ItemStack(state.getBlock(), 64);
        copied.set(DataComponentTypes.BLOCK_STATE, BlockStateComponent.DEFAULT
                .with(COLOR, state.get(COLOR))
                .with(LIT, state.get(LIT)));
        return copied;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack inHand, BlockState state, World world, BlockPos pos,
                                         PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (inHand.isOf(Items.GLOWSTONE_DUST) && Boolean.FALSE.equals(state.get(LIT))) {
            world.playSound(player, pos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.decrement(1);
            }
            world.setBlockState(pos, state.with(LIT, true));
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(AlphaesTags.Items.AXES) && Boolean.TRUE.equals(state.get(LIT))) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.damage(1, player, LivingEntity.getSlotForHand(hand));
            }
            world.setBlockState(pos, state.with(LIT, false));
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(AlphaesTags.Items.HOES) && Boolean.TRUE.equals(state.get(HAS_COVER))) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.damage(1, player, LivingEntity.getSlotForHand(hand));
            }
            world.setBlockState(pos, state.with(HAS_COVER, false));
            afterUseHoe(world, pos);
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(DYES) && !state.get(COLOR).equals(((DyeItem) inHand.getItem()).getColor())) {
            world.playSound(player, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, state.with(COLOR, ((DyeItem) inHand.getItem()).getColor()));
            if (!player.isCreative()) {
                inHand.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        if (inHand.isOf(TechBlockItems.STRIKETHROUGH_BLOCK) && !state.get(HAS_COVER)) {
            world.playSound(player, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1F, 1F);
            BlockStateComponent component = inHand.get(DataComponentTypes.BLOCK_STATE);
            DyeColor coverColor = DyeColor.WHITE;
            if (component != null) {
                coverColor = component.getValue(StrikethroughBlock.COLOR);
            }
            world.setBlockState(pos, state.with(HAS_COVER, true).with(COVER_COLOR, coverColor));
            if (!player.isCreative()) {
                inHand.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private void afterUseHoe(World world, BlockPos pos) {
        ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                new ItemStack(Items.STICK, world.random.nextBetween(1, 8)));
        itemEntity.setToDefaultPickupDelay();
        world.spawnEntity(itemEntity);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return Boolean.TRUE.equals(state.get(WATERLOGGED))
                ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView,
                                                   BlockPos pos, Direction direction, BlockPos neighborPos,
                                                   BlockState neighborState, Random random) {
        if (Boolean.TRUE.equals(state.get(WATERLOGGED))) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }
}
