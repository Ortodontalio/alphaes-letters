package com.ortodontalio.alphaesletters.client;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import static com.ortodontalio.alphaesletters.common.HasColor.COLOR;

public record MultiColorTint() implements TintSource {
    public static final MapCodec<MultiColorTint> CODEC = MapCodec.unit(new MultiColorTint());

    @Override
    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        var component = stack.get(DataComponentTypes.BLOCK_STATE);
        if (component == null) return DyeColor.WHITE.getSignColor();

        var color = component.getValue(COLOR);
        return color != null ? color.getSignColor() : DyeColor.WHITE.getSignColor();
    }

    @Override
    public MapCodec<? extends TintSource> getCodec() {
        return CODEC;
    }
}
