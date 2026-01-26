package com.ortodontalio.alphaesletters.mixin;

import com.ortodontalio.alphaesletters.migration.BlockMigrationHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.chunk.SerializedChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Inject(method = "createWorlds", at = @At("HEAD"))
    private void onCreateWorlds(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo ci) {
        System.out.println("World created!");
    }
}
