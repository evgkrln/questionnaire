package com.evgkrln.questionnaire.model;

public enum Type {
    text("Single line text"),
    textarea("Multiline text"),
    radio("Radio button"),
    checkbox("Checkbox"),
    select("Combobox"),
    date("Date");

    private String title;

    private Type(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
