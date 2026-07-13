package com.ortodontalio.alphaesletters.util.models;

import java.util.Set;

public class TagModel {
    private Set<String> values;

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }

    public void addValues(Set<String> value) {
        this.values.addAll(value);
    }
}
