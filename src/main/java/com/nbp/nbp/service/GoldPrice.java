package com.nbp.nbp.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoldPrice {
    public GoldPrice(){}

    @JsonProperty("data")
    private String date;
    @JsonProperty("cena")
    private float price;

    public String getDate() {
        return date;
    }

    public float getPrice() {
        return price;
    }
}