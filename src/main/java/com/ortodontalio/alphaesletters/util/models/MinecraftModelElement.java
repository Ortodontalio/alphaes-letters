package com.ortodontalio.alphaesletters.util.models;

import net.minecraft.util.math.Direction;

import java.util.Map;

public class MinecraftModelElement {
    private int[] from;
    private int[] to;
    private Map<Direction, MinecraftModelElementFace> faces;

    public MinecraftModelElement() {
    }

    public MinecraftModelElement(int[] from, int[] to, Map<Direction, MinecraftModelElementFace> faces) {
        this.from = from;
        this.to = to;
        this.faces = faces;
    }

    public int[] getFrom() {
        return from;
    }

    public void setFrom(int[] from) {
        this.from = from;
    }

    public int[] getTo() {
        return to;
    }

    public void setTo(int[] to) {
        this.to = to;
    }

    public Map<Direction, MinecraftModelElementFace> getFaces() {
        return faces;
    }

    public void setFaces(Map<Direction, MinecraftModelElementFace> faces) {
        this.faces = faces;
    }
}
