package com.example.demo.controller;

import com.example.demo.service.CurrencyComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/CurrencyComparison")
@RequiredArgsConstructor
public class CurrencyComparisonController {
    private CurrencyComparisonService currencyComparisonService;

    @Autowired
    public CurrencyComparisonController(CurrencyComparisonService currencyComparisonService) {
        this.currencyComparisonService = currencyComparisonService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private void getWordById(@RequestParam("currencyCode") String currencyCode, HttpServletResponse response) throws IOException {
        response.sendRedirect(currencyComparisonService.getUrl(currencyCode));
    }
}
