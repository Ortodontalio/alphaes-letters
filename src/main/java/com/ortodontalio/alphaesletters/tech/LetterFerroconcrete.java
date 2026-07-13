package com.ortodontalio.alphaesletters.tech;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ortodontalio.alphaesletters.common.Exfoliatable;
import com.ortodontalio.alphaesletters.common.HasColor;
import com.ortodontalio.alphaesletters.letters.MiscLetters;
import com.ortodontalio.alphaesletters.tags.AlphaesTags;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import com.ortodontalio.alphaesletters.util.ExfoliatableRegistry;
import com.ortodontalio.alphaesletters.util.StringProperty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Degradable;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class LetterFerroconcrete extends Block implements HasColor, Exfoliatable {
    public static final MapCodec<LetterFerroconcrete> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(Exfoliatable.ExfoliatedLevel.CODEC.fieldOf("exfoliation_state")
                            .forGetter(Degradable::getDegradationLevel), createSettingsCodec())
                    .apply(instance, LetterFerroconcrete::new)
    );
    public static final BooleanProperty LIT = Properties.LIT;
    public static final StringProperty LETTER = StringProperty.of("letter", AlphaesUtils.getAllLettersNames());
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    private final Exfoliatable.ExfoliatedLevel exfoliatedLevel;

    public LetterFerroconcrete(Exfoliatable.ExfoliatedLevel exfoliatedLevel, AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState()
                .with(LIT, false)
                .with(LETTER, MiscLetters.NONE.asString()));
        this.exfoliatedLevel = exfoliatedLevel;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, LETTER, FACING, COLOR);
    }

    @Override
    public MapCodec<LetterFerroconcrete> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack inHand, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (inHand.isOf(Items.GLOWSTONE_DUST) && Boolean.FALSE.equals(state.get(LIT))) {
            if (!player.isCreative()) {
                inHand.decrement(1);
            }
            var newState = state.with(LIT, true);
            world.setBlockState(pos, newState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(AlphaesTags.Items.AXES) && Boolean.TRUE.equals(state.get(LIT))) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.damage(1, player, LivingEntity.getSlotForHand(hand));
            }
            var newState = state.with(LIT, false);
            world.setBlockState(pos, newState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(AlphaesTags.Items.FARBA_ITEMS) && exfoliatedLevel == ExfoliatedLevel.EXFOLIATED) {
            var correspondingDye = ExfoliatableRegistry.getCorrespondingDye(state.getBlock());
            if (correspondingDye.isEmpty() || !inHand.isOf(correspondingDye.get())) {
                return ActionResult.PASS;
            }
            world.playSound(player, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.damage(1, player, LivingEntity.getSlotForHand(hand));
            }
            world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, pos, 0);
            ExfoliatableRegistry.getDecreasedExfoliatedState(state)
                    .ifPresent(decreasedState -> {
                        world.setBlockState(pos, decreasedState, Block.NOTIFY_ALL_AND_REDRAW);
                        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, decreasedState));
                    });
            return ActionResult.SUCCESS;
        }
        if (inHand.isOf(Items.BRUSH)) {
            world.playSound(player, pos, SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.damage(10, player, LivingEntity.getSlotForHand(hand));
            }
            world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, pos, 0);
            ExfoliatableRegistry.getIncreasedExfoliatedState(state)
                    .ifPresent(decreasedState -> {
                        world.setBlockState(pos, decreasedState, Block.NOTIFY_ALL_AND_REDRAW);
                        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, decreasedState));
                    });
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    public Exfoliatable.ExfoliatedLevel getDegradationLevel() {
        return this.exfoliatedLevel;
    }

    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool,
                                   boolean dropExperience) {
        RegistryWrapper.WrapperLookup wrapperLookup = world.getRegistryManager();

        RegistryEntry<Enchantment> silkTouch = wrapperLookup
                .getOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(Enchantments.SILK_TOUCH);
        boolean hasSilkTouch = EnchantmentHelper.getLevel(silkTouch, tool) > 0;
        if (!hasSilkTouch) {
            BlockState bars = Blocks.IRON_BARS.getDefaultState();
            world.setBlockState(pos, bars);
        }
    }
}
