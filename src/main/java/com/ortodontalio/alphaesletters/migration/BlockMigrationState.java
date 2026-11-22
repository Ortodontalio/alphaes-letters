package com.ortodontalio.alphaesletters.migration;

import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class BlockMigrationState extends PersistentState {
    private static final String NAME = "alphaesletters_migration";
    private int migrationVersion = 0;

    public BlockMigrationState() {
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putInt("migrationVersion", migrationVersion);
        return nbt;
    }

    public int getMigrationVersion() {
        return migrationVersion;
    }

    public void setMigrationVersion(int version) {
        this.migrationVersion = version;
        markDirty();
    }

    public static BlockMigrationState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        BlockMigrationState state = new BlockMigrationState();
        state.migrationVersion = nbt.getInt("migrationVersion");
        return state;
    }

    public static BlockMigrationState getOrCreate(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(
                getType(),
                NAME
        );
    }

    public static Type<BlockMigrationState> getType() {
        return new Type<>(
                BlockMigrationState::new,
                BlockMigrationState::fromNbt,
                DataFixTypes.SAVED_DATA_RANDOM_SEQUENCES
        );
    }
}

