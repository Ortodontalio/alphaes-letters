package com.ortodontalio.alphaesletters.addon_system.models;

public class MinecraftModelElementFace {
    private int[] uv;

    public MinecraftModelElementFace() {
    }

    public MinecraftModelElementFace(int[] uv) {
        this.uv = uv;
    }

    public int[] getUv() {
        return uv;
    }

    public void setUv(int[] uv) {
        this.uv = uv;
    }

    public int getTintindex() {
        return 0;
    }

    public String getTexture() {
        return "#2";
    }
}
