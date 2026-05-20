package com.yabetslemma.landrouter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "land-router")
public class LandRouterProperties {

    private String countriesMap;

    public String getCountriesMap() {
        return countriesMap;
    }

    public void setCountriesMap(String countriesMap) {
        this.countriesMap = countriesMap;
    }
}