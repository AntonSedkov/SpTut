package com.example.consumerest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    private Value value;

    public Quote() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Quote.class.getSimpleName() + "{", "}")
                .add("type='" + type + "'")
                .add("value=" + value)
                .toString();
    }
}