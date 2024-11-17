package com.ortodontalio.alphaesletters.handlers;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class AlphaesScreenHandlers {

    private AlphaesScreenHandlers() {
    }

    public static final ScreenHandlerType<DyeingMachineScreenHandler> DYEING_MACHINE_SCREEN_HANDLER =
            register("dyeing_machine", DyeingMachineScreenHandler::new, BlockPositionPayload.PACKET_CODEC);

    private static <T extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<T, D> register(String id,
           ExtendedScreenHandlerType.ExtendedFactory<T, D> factory, PacketCodec<? super RegistryByteBuf, D> codec) {
        return Registry.register(Registries.SCREEN_HANDLER, RegistryKey.of(RegistryKeys.SCREEN_HANDLER, Identifier.of(MOD_ID, id)),
                new ExtendedScreenHandlerType<>(factory, codec));
    }
}
