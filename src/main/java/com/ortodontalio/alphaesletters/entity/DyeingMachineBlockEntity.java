package com.ortodontalio.alphaesletters.entity;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.DyeingMachine;
import com.ortodontalio.alphaesletters.handlers.DyeingMachineScreenHandler;
import com.ortodontalio.alphaesletters.inventory.ImplementedInventory;
import com.ortodontalio.alphaesletters.recipe.DyeingMachineRecipe;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Optional;

import static com.ortodontalio.alphaesletters.common.DyeingMachine.LIT;
import static com.ortodontalio.alphaesletters.common.DyeingMachine.WATERED;

public class DyeingMachineBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private static final String PROGRESS_KEY = String.format("%s.progress", DyeingMachine.ID);
    private static final String FUEL_TIME_KEY = String.format("%s.fuelTime", DyeingMachine.ID);
    private static final String MAX_FUEL_TIME_KEY = String.format("%s.maxFuelTime", DyeingMachine.ID);
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    public static final int NO_WATERED = 0;
    public static final int HALF_WATERED = 1;
    public static final int FULL_WATERED = 2;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 360;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public DyeingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(AlphaesBlockEntities.dyeingMachineEntity, pos, state);
        FuelRegistry.INSTANCE.add(Items.WATER_BUCKET, 5000);
        propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> DyeingMachineBlockEntity.this.progress;
                    case 1 -> DyeingMachineBlockEntity.this.maxProgress;
                    case 2 -> DyeingMachineBlockEntity.this.fuelTime;
                    case 3 -> DyeingMachineBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DyeingMachineBlockEntity.this.progress = value;
                    case 1 -> DyeingMachineBlockEntity.this.maxProgress = value;
                    case 2 -> DyeingMachineBlockEntity.this.fuelTime = value;
                    case 3 -> DyeingMachineBlockEntity.this.maxFuelTime = value;
                    default -> {
                    }
                }
            }

            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(String.format("block.%s.%s", AlphaesLetters.MOD_ID, DyeingMachine.ID));
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DyeingMachineScreenHandler(syncId, inv, this, propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt(PROGRESS_KEY, progress);
        nbt.putInt(FUEL_TIME_KEY, fuelTime);
        nbt.putInt(MAX_FUEL_TIME_KEY, maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt(PROGRESS_KEY);
        fuelTime = nbt.getInt(FUEL_TIME_KEY);
        maxFuelTime = nbt.getInt(MAX_FUEL_TIME_KEY);
    }

    public void consumeFuel(World world, BlockState state, BlockPos pos) {
        if (getStack(0).isOf(Items.WATER_BUCKET)) {
            fillWater(world, state, pos);
            setStack(0, Items.BUCKET.getDefaultStack());
        }
    }

    public void fillWater(World world, BlockState state, BlockPos pos) {
        fuelTime = FuelRegistry.INSTANCE.get(Items.WATER_BUCKET);
        maxFuelTime = fuelTime;
        world.setBlockState(pos, state.with(WATERED, FULL_WATERED).with(LIT, true), Block.NOTIFY_ALL);
        world.playSound(null, pos, SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 0.2F, 0.2F);
    }

    public void emptyWater(World world, BlockState state, BlockPos pos) {
        fuelTime = 0;
        maxFuelTime = fuelTime;
        world.setBlockState(pos, state.with(WATERED, NO_WATERED).with(LIT, false), Block.NOTIFY_ALL);
        world.playSound(null, pos, SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 0.15F, 0.001F);
    }

    private static boolean isFuelLessHalf(DyeingMachineBlockEntity entity) {
        return entity.fuelTime < entity.maxFuelTime / 1.65;
    }

    public static void tick(World world, BlockPos pos, BlockState state, DyeingMachineBlockEntity entity) {
        if (!hasPowderInResSlot(entity)) {
            entity.resetProgress();
        }
        if (hasFuelInFuelSlot(entity)) {
            entity.consumeFuel(world, state, pos);
        }
        if (hasRecipe(entity)) {
            workProcess(world, pos, state, entity);
        }
        if (state.get(WATERED) == NO_WATERED) {
            world.setBlockState(pos, state.with(LIT, false), Block.FORCE_STATE);
        }
    }

    private static void workProcess(World world, BlockPos pos, BlockState state, DyeingMachineBlockEntity entity) {
        if (isConsumingFuel(entity)) {
            entity.progress++;
            entity.fuelTime--;
            if (isFuelLessHalf(entity) && state.get(WATERED) == FULL_WATERED) {
                world.setBlockState(pos, state.with(WATERED, HALF_WATERED), Block.NOTIFY_ALL);
            }
            if (entity.progress > entity.maxProgress) {
                craftItem(entity);
                world.setBlockState(pos, state.with(LIT, true), Block.FORCE_STATE);
            }
        } else {
            if (state.get(WATERED) != NO_WATERED) {
                world.setBlockState(pos, state.with(WATERED, NO_WATERED).with(LIT, false), Block.NOTIFY_ALL);
            }
        }
        markDirty(world, pos, state);
    }

    private static boolean hasFuelInFuelSlot(DyeingMachineBlockEntity entity) {
        return !entity.getStack(0).isEmpty();
    }

    private static boolean hasPowderInResSlot(DyeingMachineBlockEntity entity) {
        return !entity.getStack(1).isEmpty();
    }

    private static boolean isConsumingFuel(DyeingMachineBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    public static boolean isFullyWatered(DyeingMachineBlockEntity entity) {
        return isConsumingFuel(entity) && entity.fuelTime == entity.maxFuelTime;
    }

    private static boolean hasRecipe(DyeingMachineBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<RecipeEntry<DyeingMachineRecipe>> match = Objects.requireNonNull(world).getRecipeManager()
                .getFirstMatch(DyeingMachineRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().value().getResult(null));
    }

    private static void craftItem(DyeingMachineBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());

        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<RecipeEntry<DyeingMachineRecipe>> match = Objects.requireNonNull(world).getRecipeManager()
                .getFirstMatch(DyeingMachineRecipe.Type.INSTANCE, inventory, world);

        if (match.isPresent()) {
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);
            entity.setStack(3, new ItemStack(match.get().value().getResult(null).getItem(),
                    entity.getStack(3).getCount() + 1));
            entity.resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }

}
