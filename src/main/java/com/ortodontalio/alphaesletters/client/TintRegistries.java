package com.ortodontalio.alphaesletters.client;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.util.Identifier;

public final class TintRegistries {
    private static final Identifier MULTI_COLOR_TINT_ID =
            Identifier.of(AlphaesLetters.MOD_ID, "multi_color_tint");

    public static void registerAll() {
        TintSourceTypes.ID_MAPPER.put(MULTI_COLOR_TINT_ID, MultiColorTint.CODEC);
    }
}
