package com.ortodontalio.alphaesletters.util;

import net.minecraft.state.property.Property;

import java.util.List;
import java.util.Optional;

public class StringProperty extends Property<String> {

    private final List<String> values;

    protected StringProperty(String name, List<String> values) {
        super(name, String.class);
        this.values = values;
    }

    public static StringProperty of(String name, List<String> values) {
        return new StringProperty(name, values);
    }

    @Override
    public List<String> getValues() {
        return values;
    }

    @Override
    public String name(String value) {
        return value;
    }

    @Override
    public Optional<String> parse(String name) {
        return values.stream().filter(val -> val.equals(name)).findFirst();
    }

    @Override
    public int ordinal(String value) {
        return values.indexOf(value);
    }
}
