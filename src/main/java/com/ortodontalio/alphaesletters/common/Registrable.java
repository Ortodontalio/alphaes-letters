package com.ortodontalio.alphaesletters.common;

import net.minecraft.registry.RegistryKey;

public interface Registrable<T> {
    RegistryKey<T> getRegistryKey();
}
