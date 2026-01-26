package com.ortodontalio.alphaesletters.mixin;

import com.ortodontalio.alphaesletters.migration.BlockMigrationHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.chunk.SerializedChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SerializedChunk.class)
public abstract class SerializedChunkMixin {

    @Inject(method = "fromNbt", at = @At("HEAD"))
    private static void onFromNbt(HeightLimitView world,
                                  DynamicRegistryManager registryManager,
                                  NbtCompound nbt,
                                  CallbackInfoReturnable<SerializedChunk> cir) {
        BlockMigrationHandler.migrateChunkNbt(nbt);
    }
}
