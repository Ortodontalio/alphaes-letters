package com.ortodontalio.alphaesletters.util.models;

import java.util.List;

public class MinecraftModel {
    private List<MinecraftModelElement> elements;

    public MinecraftModel() {
    }

    public MinecraftModel(List<MinecraftModelElement> elements) {
        this.elements = elements;
    }

    public String getParent() {
        return "alphaesletters:block/letter_template";
    }

    public List<MinecraftModelElement> getElements() {
        return elements;
    }

    public void setElements(List<MinecraftModelElement> elements) {
        this.elements = elements;
    }
}
