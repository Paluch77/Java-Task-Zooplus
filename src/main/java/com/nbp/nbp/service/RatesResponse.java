package com.nbp.nbp.service;

import java.util.List;

public class RatesResponse {

    public RatesResponse(){}

    private String code;
    private List<Rate> rates;

    public List<Rate> getRates(){
        return rates;
    }

    public String getCode(){
        return code;
    }
}