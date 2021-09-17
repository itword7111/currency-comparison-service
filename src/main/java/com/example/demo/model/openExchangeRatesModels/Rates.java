package com.example.demo.model.openExchangeRatesModels;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("jsonschema2pojo")
public class Rates {

    @JsonIgnore
    private Map<String, Double> additionalProperties = new HashMap<String, Double>();
    @JsonAnyGetter
    public Map<String, Double> getAdditionalProperties() {
        return this.additionalProperties;
    }
    @JsonAnyGetter
    public Double getAdditionalProperty(String key) {
        return this.additionalProperties.get(key);
    }
    @JsonAnySetter
    public void setAdditionalProperty(String name, Double value) {
        this.additionalProperties.put(name, value);
    }

}