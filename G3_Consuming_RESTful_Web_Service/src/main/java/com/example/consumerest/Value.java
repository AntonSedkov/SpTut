package com.example.consumerest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private Long id;
    private String quote;

    public Value() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Value.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("quote='" + quote + "'")
                .toString();
    }
}
