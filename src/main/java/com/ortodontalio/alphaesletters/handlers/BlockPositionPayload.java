package com.ortodontalio.alphaesletters.handlers;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public record BlockPositionPayload(BlockPos pos) implements CustomPayload {
    public static final PacketCodec<RegistryByteBuf, BlockPositionPayload> PACKET_CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, BlockPositionPayload::pos, BlockPositionPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return new Id<>(Identifier.of(MOD_ID, "block_pos"));
    }
}