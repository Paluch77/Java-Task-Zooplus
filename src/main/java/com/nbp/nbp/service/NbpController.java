package com.nbp.nbp.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class NbpController {

    private final NbpService nbpService;

    public NbpController(NbpService nbpService){
        this.nbpService = nbpService;
    }

    @GetMapping("/exchange-rates/{currencyCode}")
    @ResponseStatus(HttpStatus.OK)
    public RatesResponse getCurrencyExchangeRateToPlnForLastFiveDays(@PathVariable String currencyCode){
        return nbpService.provideCurrencyRateToPln(currencyCode, 5);
    }

    @GetMapping("/gold-price/average")
    @ResponseStatus(HttpStatus.OK)
    public String getAverageGoldPriceForLastTwoWeeks(){
        return nbpService.provideAverageGoldPrice(14);
    }
}
