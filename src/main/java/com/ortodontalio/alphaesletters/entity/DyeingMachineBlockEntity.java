package com.ortodontalio.alphaesletters.entity;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.handlers.BlockPositionPayload;
import com.ortodontalio.alphaesletters.handlers.DyeingMachineScreenHandler;
import com.ortodontalio.alphaesletters.inventory.ImplementedInventory;
import com.ortodontalio.alphaesletters.recipe.DyeingMachineRecipe;
import com.ortodontalio.alphaesletters.tech.DyeingMachine;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
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
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

import static com.ortodontalio.alphaesletters.tech.DyeingMachine.LIT;
import static com.ortodontalio.alphaesletters.tech.DyeingMachine.WATERED;

public class DyeingMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPositionPayload>, ImplementedInventory {
    private static final String PROGRESS_KEY = String.format("%s.progress", DyeingMachine.ID);
    private static final String FUEL_TIME_KEY = String.format("%s.fuelTime", DyeingMachine.ID);
    private static final String MAX_FUEL_TIME_KEY = String.format("%s.maxFuelTime", DyeingMachine.ID);
    private final SimpleInventory inventory = new SimpleInventory(4);
    public static final int NO_WATERED = 0;
    public static final int HALF_DOWN_WATERED = 1;
    public static final int HALF_WATERED = 2;
    public static final int HALF_UP_WATERED = 3;
    public static final int FULL_WATERED = 4;
    public static final int WATER_BUCKET_FUEL_STRENGTH = 5000;
    private final ServerRecipeManager.MatchGetter<DyeingMachineRecipeInput, DyeingMachineRecipe> matchGetter;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 360;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public DyeingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(AlphaesBlockEntities.dyeingMachineEntity, pos, state);
        this.matchGetter = ServerRecipeManager.createCachedMatchGetter(DyeingMachineRecipe.Type.INSTANCE);
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
                    default -> throw new IllegalStateException("Unexpected index: " + index);
                }
            }

            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory.getHeldStacks();
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
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        Inventories.writeNbt(nbt, getItems(), registries);
        nbt.putInt(PROGRESS_KEY, progress);
        nbt.putInt(FUEL_TIME_KEY, fuelTime);
        nbt.putInt(MAX_FUEL_TIME_KEY, maxFuelTime);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        Inventories.readNbt(nbt, getItems(), registries);
        progress = nbt.getInt(PROGRESS_KEY);
        fuelTime = nbt.getInt(FUEL_TIME_KEY);
        maxFuelTime = nbt.getInt(MAX_FUEL_TIME_KEY);
    }

    public void fillWater(World world, BlockPos pos) {
        fuelTime = WATER_BUCKET_FUEL_STRENGTH;
        maxFuelTime = fuelTime;
        setWateredState(world, FULL_WATERED);
        world.playSound(null, pos, SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 0.2F, 0.2F);
    }

    public void emptyWater(World world, BlockPos pos) {
        fuelTime = 0;
        maxFuelTime = fuelTime;
        setLitState(world, false);
        setWateredState(world, NO_WATERED);
        world.playSound(null, pos, SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 0.15F, 0.001F);
    }

    private static boolean isFuelLessHalf(DyeingMachineBlockEntity entity) {
        return entity.fuelTime < entity.maxFuelTime / 2;
    }

    private static boolean isFuelLessHalfUp(DyeingMachineBlockEntity entity) {
        return entity.fuelTime < entity.maxFuelTime / 1.4;
    }

    private static boolean isFuelLessHalfDown(DyeingMachineBlockEntity entity) {
        return entity.fuelTime < entity.maxFuelTime / 3.4;
    }

    private void setLitState(World world, boolean value) {
        world.setBlockState(pos, getCachedState().with(LIT, value));
    }

    private void setWateredState(World world, int value) {
        world.setBlockState(pos, getCachedState().with(WATERED, value));
    }

    public static void tick(World world, BlockPos pos, BlockState state, DyeingMachineBlockEntity entity) {
        if (!world.isReceivingRedstonePower(pos)) {
            if (!hasPowderInResSlot(entity)) {
                entity.resetProgress();
            }
            if (hasFuelInFuelSlot(entity)) {
                entity.fillWater(world, pos);
                entity.setStack(0, Items.BUCKET.getDefaultStack());
            }
            if (hasRecipe(world, entity)) {
                workProcess(world, state, entity);
                entity.markDirty();
            } else if (world instanceof ServerWorld) {
                entity.setLitState(world, false);
            }
        } else {
            entity.setLitState(world, false);
        }
    }

    private static void workProcess(World world, BlockState state, DyeingMachineBlockEntity entity) {
        if (isConsumingFuel(entity)) {
            entity.setLitState(world, true);
            entity.progress++;
            entity.fuelTime--;
            if (isFuelLessHalfUp(entity) && state.get(WATERED) == FULL_WATERED) {
                entity.setWateredState(world, HALF_UP_WATERED);
            }
            if (isFuelLessHalf(entity) && state.get(WATERED) == HALF_UP_WATERED) {
                entity.setWateredState(world, HALF_WATERED);
            }
            if (isFuelLessHalfDown(entity) && state.get(WATERED) == HALF_WATERED) {
                entity.setWateredState(world, HALF_DOWN_WATERED);
            }
            if (entity.progress > entity.maxProgress) {
                craftItem(world, entity);
                entity.setLitState(world, true);
            }
        } else {
            if (state.get(WATERED) != NO_WATERED) {
                entity.setLitState(world, false);
                entity.setWateredState(world, NO_WATERED);
            }
        }
    }

    private static boolean hasFuelInFuelSlot(DyeingMachineBlockEntity entity) {
        return !entity.getStack(0).isEmpty() && entity.getStack(0).isOf(Items.WATER_BUCKET);
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

    private static boolean hasRecipe(World world, DyeingMachineBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if (world instanceof ServerWorld serverWorld) {
            Optional<RecipeEntry<DyeingMachineRecipe>> match = entity.matchGetter
                    .getFirstMatch(new DyeingMachineRecipeInput(inventory.getStack(0), inventory.getStack(1),
                            inventory.getStack(2), inventory.getStack(3)), serverWorld);
            return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                    && canInsertItemIntoOutputSlot(inventory, match.get().value().getResult());
        }
        return false;
    }

    private static void craftItem(World world, DyeingMachineBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        if (world instanceof ServerWorld serverWorld) {
            Optional<RecipeEntry<DyeingMachineRecipe>> match = entity.matchGetter
                    .getFirstMatch(new DyeingMachineRecipeInput(inventory.getStack(0), inventory.getStack(1), inventory.getStack(2),
                            inventory.getStack(3)), serverWorld);
            if (match.isPresent()) {
                entity.removeStack(1, 1);
                entity.removeStack(2, 1);
                entity.setStack(3, new ItemStack(match.get().value().getResult().getItem(),
                        entity.getStack(3).getCount() + 1));
                entity.resetProgress();
            }
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

    @Override
    public BlockPositionPayload getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new BlockPositionPayload(pos);
    }

    public record DyeingMachineRecipeInput(ItemStack fuel, ItemStack resource, ItemStack farba,
                                           ItemStack result) implements RecipeInput {
        @Override
        public ItemStack getStackInSlot(int slot) {
            return switch (slot) {
                case 0 -> fuel;
                case 1 -> resource;
                case 2 -> farba;
                case 3 -> result;
                default -> throw new IllegalArgumentException("Recipe does not contain slot " + slot);
            };
        }

        @Override
        public int size() {
            return 4;
        }

        @Override
        public boolean isEmpty() {
            return fuel.isEmpty() && resource.isEmpty() && farba.isEmpty() && result.isEmpty();
        }
    }
}
