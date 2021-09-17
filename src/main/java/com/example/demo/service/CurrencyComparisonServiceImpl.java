package com.example.demo.service;

import com.example.demo.client.GiphyClient;
import com.example.demo.client.OpenExchangeRatesClient;
import com.example.demo.model.openExchangeRatesModels.OpenExchangeRatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
public class CurrencyComparisonServiceImpl implements CurrencyComparisonService{
    private final OpenExchangeRatesClient historicalCurrencyResponseClient;
    private final GiphyClient giphyClient;
    private final String baseCurrency;

    @Autowired
    public CurrencyComparisonServiceImpl(OpenExchangeRatesClient historicalCurrencyResponseClient, GiphyClient giphyClient, @Value("${baseCurrency}") String baseCurrency) {
        this.historicalCurrencyResponseClient = historicalCurrencyResponseClient;
        this.giphyClient=giphyClient;
        this.baseCurrency=baseCurrency;
    }
    @Override
    public String getUrl(String currencyCode){
        String ratesSymbols = currencyCode +","+baseCurrency;
        OpenExchangeRatesResponse yesterdayCurrenciesValue=historicalCurrencyResponseClient.getHistoricalCurrencyResponse(LocalDate.now().minusDays(1).toString(), ratesSymbols);
        OpenExchangeRatesResponse todayCurrenciesValue=historicalCurrencyResponseClient.getLatestCurrencyResponse(ratesSymbols);

        double yesterdayRatio = (1/yesterdayCurrenciesValue.getRates().getAdditionalProperty(currencyCode))
                *yesterdayCurrenciesValue.getRates().getAdditionalProperty(baseCurrency);
        double todayRatio = (1/todayCurrenciesValue.getRates().getAdditionalProperty(currencyCode))
                *todayCurrenciesValue.getRates().getAdditionalProperty(baseCurrency);
        if(todayRatio>yesterdayRatio){
            return giphyClient.getGiphyResponse("rich").getData().getEmbedUrl();
        }
        else {
            return giphyClient.getGiphyResponse("broke").getData().getEmbedUrl();
        }

    }
}
