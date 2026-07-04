package com.ortodontalio.alphaesletters.util.models;

import java.util.List;

public class MinecraftModel {
    private String name;
    private String author = "Anonymous";
    private String version = "1.0";
    private List<MinecraftModelElement> elements;

    public MinecraftModel() {
    }

    public MinecraftModel(List<MinecraftModelElement> elements) {
        this.elements = elements;
    }

    public String getParent() {
        return "alphaesletters:block/letter_template";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<MinecraftModelElement> getElements() {
        return elements;
    }

    public void setElements(List<MinecraftModelElement> elements) {
        this.elements = elements;
    }
}
