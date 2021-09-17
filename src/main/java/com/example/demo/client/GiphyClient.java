package com.example.demo.client;

import com.example.demo.model.giphyModels.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="${GiphyUrl}",name = "Giphy-Client")
public interface GiphyClient {

    @GetMapping("random?api_key=${GiphyApiId}&tag={tag}")
    GiphyResponse getGiphyResponse(@PathVariable String tag);
}
