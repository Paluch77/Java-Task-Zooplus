package com.nbp.nbp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class NbpService {

    private static final String API_URL = "http://api.nbp.pl/api/";
    private static final String CURRENCY_RATE_DATES = "exchangerates/rates/a/{code}/{startDate}/{endDate}/";
    private static final String GOLD_PRICES = "cenyzlota/{startDate}/{endDate}";

    private final RestTemplate restTemplate;

    public NbpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RatesResponse provideCurrencyRateToPln(String currencyCode, int numberOfDays) {
        LocalDate currentDate = LocalDate.now();
        String dateNow = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dateFiveDaysAgo = currentDate.minusDays(numberOfDays).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return restTemplate.getForObject(API_URL + CURRENCY_RATE_DATES, RatesResponse.class, currencyCode, dateFiveDaysAgo, dateNow);

    }

    public String provideAverageGoldPrice(int numberOfDays) {
        float sumOfGoldPrices = 0;
        LocalDate currentDate = LocalDate.now();
        String dateNow = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dateFiveDaysAgo = currentDate.minusDays(numberOfDays).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        GoldPrice[] goldPrices = restTemplate.getForObject(API_URL + GOLD_PRICES, GoldPrice[].class, dateFiveDaysAgo, dateNow);

        if(goldPrices == null || goldPrices.length == 0){
            return "Error withdrawing gold prices.";
        }

        for(GoldPrice goldPrice : goldPrices){
            sumOfGoldPrices =  sumOfGoldPrices + goldPrice.getPrice();
        }

        return String.valueOf(sumOfGoldPrices / goldPrices.length);
    }
}