package com.example.demo.service;

import com.example.demo.client.GiphyClient;
import com.example.demo.client.OpenExchangeRatesClient;
import com.example.demo.model.giphyModels.Data;
import com.example.demo.model.giphyModels.GiphyResponse;
import com.example.demo.model.openExchangeRatesModels.OpenExchangeRatesResponse;
import com.example.demo.model.openExchangeRatesModels.Rates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application.properties")
class CurrencyComparisonServiceImplTest {
@MockBean
    GiphyClient giphyClient ;
@MockBean
    OpenExchangeRatesClient openExchangeRatesClient;

    @Test
    void getUrl() {

        CurrencyComparisonService currencyComparisonService=new CurrencyComparisonServiceImpl(openExchangeRatesClient, giphyClient, "RUB");

        OpenExchangeRatesResponse yesterdayOpenExchangeRatesResponse=new OpenExchangeRatesResponse();
        Rates yesterdayOpenExchangeRates=new Rates();
        yesterdayOpenExchangeRates.setAdditionalProperty("RUB", 70.0);
        yesterdayOpenExchangeRates.setAdditionalProperty("EUR", 0.8);
        yesterdayOpenExchangeRatesResponse.setRates(yesterdayOpenExchangeRates);

        OpenExchangeRatesResponse todayOpenExchangeRatesResponse=new OpenExchangeRatesResponse();
        Rates todayOpenExchangeRates=new Rates();
        todayOpenExchangeRates.setAdditionalProperty("RUB", 71.0);
        todayOpenExchangeRates.setAdditionalProperty("EUR", 0.8);
        todayOpenExchangeRatesResponse.setRates(todayOpenExchangeRates);

        GiphyResponse giphyResponseRich = new GiphyResponse();
        Data dataRich=new Data();
        dataRich.setEmbedUrl("https://giphy.com/embed/123456789123456789");
        giphyResponseRich.setData(dataRich);

        GiphyResponse giphyResponseBroke = new GiphyResponse();
        Data dataBroke=new Data();
        dataBroke.setEmbedUrl("https://giphy.com/embed/223456789123456789");
        giphyResponseBroke.setData(dataBroke);

        given(this.openExchangeRatesClient.getHistoricalCurrencyResponse(LocalDate.now().minusDays(1).toString(),"EUR,RUB")).willReturn(yesterdayOpenExchangeRatesResponse);
        given(this.openExchangeRatesClient.getLatestCurrencyResponse("EUR,RUB")).willReturn(todayOpenExchangeRatesResponse);
        given(this.giphyClient.getGiphyResponse("rich")).willReturn(giphyResponseRich);
        given(this.giphyClient.getGiphyResponse("broke")).willReturn(giphyResponseBroke);

        String outputUrl =currencyComparisonService.getUrl("EUR");
        Assertions.assertEquals("https://giphy.com/embed/123456789123456789", outputUrl);

    }
}