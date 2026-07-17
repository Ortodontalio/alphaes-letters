package com.ortodontalio.alphaesletters.mixin;

import com.ortodontalio.alphaesletters.addon_system.AddonResourceLoader;
import com.ortodontalio.alphaesletters.addon_system.AddonResourcePack;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackInfo;
import net.minecraft.resource.ResourcePackPosition;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Arrays;

@Mixin(net.minecraft.resource.ResourcePackManager.class)
public abstract class ResourcePackManagerMixin {

    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
    private static ResourcePackProvider[] addOpenLettersProvider(ResourcePackProvider[] original) {
        ResourcePackProvider[] newProviders = Arrays.copyOf(original, original.length + 1);
        newProviders[original.length] = profileAdder -> {
            AddonResourcePack pack = AddonResourceLoader.GENERATED_PACK;

            ResourcePackInfo info = pack.getInfo();
            ResourcePackProfile.PackFactory packFactory = new ResourcePackProfile.PackFactory() {
                @Override
                public ResourcePack open(ResourcePackInfo info) {
                    return pack;
                }

                @Override
                public ResourcePack openWithOverlays(ResourcePackInfo info, ResourcePackProfile.Metadata metadata) {
                    return pack;
                }
            };
            ResourcePackProfile.Metadata metadata = ResourcePackProfile.loadMetadata(info, packFactory, 61);
            if (metadata == null) {
                return;
            }

            ResourcePackPosition position = new ResourcePackPosition(true, ResourcePackProfile.InsertionPosition.TOP,
                    true);
            ResourcePackProfile profile = new ResourcePackProfile(info, packFactory, metadata, position);

            profileAdder.accept(profile);
        };
        return newProviders;
    }
}