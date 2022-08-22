package com.ortodontalio.alphaesletters.handlers;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesScreenHandlers {
    @SuppressWarnings("deprecation")
    public static ScreenHandlerType<DyeingMachineScreenHandler> DYEING_MACHINE_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "dyeing_machine"),
                    DyeingMachineScreenHandler::new);
}
