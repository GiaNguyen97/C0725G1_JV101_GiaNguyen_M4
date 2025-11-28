package org.example.demo_spring_mvc.entity;

public class Currency {

    private String type;
    private double rate;
    private double amount;

    public Currency() {}

    public Currency(String type, double rate, double amount) {
        this.type = type;
        this.rate = rate;
        this.amount = amount;
    }

    // getters / setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}