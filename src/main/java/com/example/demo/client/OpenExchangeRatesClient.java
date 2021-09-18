package com.example.demo.client;

import com.example.demo.model.openExchangeRatesModels.OpenExchangeRatesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="${OpenExchangeRatesUrl}",name = "OpenExchangeRates-Client")
public interface OpenExchangeRatesClient {

    //@GetMapping("/2020-07-10.json?app_id=024dda1c756c47c4ae8d3988a1496ee5&symbols=RUB,EUR")
    @GetMapping("/historical/{date}.json?app_id=${OpenExchangeRatesApiId}&symbols={currencyCode}")
    OpenExchangeRatesResponse getHistoricalCurrencyResponse(@PathVariable String date, @PathVariable String currencyCode);

    @GetMapping("/latest.json?app_id=${OpenExchangeRatesApiId}&symbols={currencyCode}")
    OpenExchangeRatesResponse getLatestCurrencyResponse(@PathVariable String currencyCode);
}
