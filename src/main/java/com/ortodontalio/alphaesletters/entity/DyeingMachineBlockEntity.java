package com.ortodontalio.alphaesletters.entity;

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
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

import static com.ortodontalio.alphaesletters.common.DyeingMachine.LIT;
import static com.ortodontalio.alphaesletters.common.DyeingMachine.WATERED;

public class DyeingMachineBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 360;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public DyeingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(AlphaesBlockEntities.DYEING_MACHINE_BLOCK_ENTITY, pos, state);
        FuelRegistry.INSTANCE.add(Items.WATER_BUCKET, 5000);
        this.propertyDelegate = new PropertyDelegate() {
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
                switch(index) {
                    case 0: DyeingMachineBlockEntity.this.progress = value; break;
                    case 1: DyeingMachineBlockEntity.this.maxProgress = value; break;
                    case 2: DyeingMachineBlockEntity.this.fuelTime = value; break;
                    case 3: DyeingMachineBlockEntity.this.maxFuelTime = value; break;
                    default: break;
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
        return Text.translatable("block.alphaesletters.dyeing_machine");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DyeingMachineScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("dyeing_machine.progress", progress);
        nbt.putInt("dyeing_machine.fuelTime", fuelTime);
        nbt.putInt("dyeing_machine.maxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("dyeing_machine.progress");
        fuelTime = nbt.getInt("dyeing_machine.fuelTime");
        maxFuelTime = nbt.getInt("dyeing_machine.maxFuelTime");
    }

    private void consumeFuel(World world, BlockState state, BlockPos pos) {
        if(getStack(0).isOf(Items.WATER_BUCKET)) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.getStack(0).getItem());
            this.maxFuelTime = this.fuelTime;
            this.setStack(0, new ItemStack(Items.BUCKET, 1));
            world.setBlockState(pos, state.with(WATERED, 2).with(LIT, true), Block.NOTIFY_ALL);
        }
    }

    private static boolean isFuelLessHalf(DyeingMachineBlockEntity entity) {
        return entity.fuelTime < entity.maxFuelTime / 2;
    }

    @SuppressWarnings("unused")
    public static void tick(World world, BlockPos pos, BlockState state, DyeingMachineBlockEntity entity) {
        if(!hasPowderInResSlot(entity)) {
            entity.resetProgress();
        }
        if(hasFuelInFuelSlot(entity)) {
            entity.consumeFuel(world, state, pos);
        }
        if(hasRecipe(entity)) {
            if(isConsumingFuel(entity)) {
                entity.progress++;
                entity.fuelTime--;
                if (isFuelLessHalf(entity) && state.get(WATERED) == 2) {
                    world.setBlockState(pos, state.with(WATERED, 1), Block.NOTIFY_ALL);
                }
                if (entity.progress > entity.maxProgress) {
                    craftItem(entity);
                }
            }
            else {
                if(state.get(WATERED) != 0) {
                    world.setBlockState(pos, state.with(WATERED, 0).with(LIT, false), Block.NOTIFY_ALL);
                }
            }
        }
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

    private static boolean hasRecipe(DyeingMachineBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<DyeingMachineRecipe> match = Objects.requireNonNull(world).getRecipeManager()
                .getFirstMatch(DyeingMachineRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(DyeingMachineBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());

        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<DyeingMachineRecipe> match = Objects.requireNonNull(world).getRecipeManager()
                .getFirstMatch(DyeingMachineRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            entity.removeStack(1,1);
            entity.removeStack(2,1);
            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 1));
            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }

}
