package org.example.demo_spring_mvc.entity;

public class Word {
    private String key;
    private String meaning;

    public Word() {}

    public Word(String key, String meaning) {
        this.key = key;
        this.meaning = meaning;
    }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }
}