package com.ortodontalio.alphaesletters;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public final class AlphaesGameRules {
    public static final GameRules.Key<GameRules.BooleanRule> EXFOLIATION_GR =
            GameRuleRegistry.register(
                    "exfoliationEnabled",
                    GameRules.Category.UPDATES,
                    GameRuleFactory.createBooleanRule(true) // Значение по умолчанию: true
            );

    public static void registerAll() {
        // Empty, since all game rules have been registered in static way.
    }
}
