package com.yabetslemma.landrouter.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.yabetslemma.landrouter.dto.Country;

@Configuration
public class LandRouterConfig {

    @Bean("countryGraph")
    public Map<String, List<String>> initGraph(LandRouterProperties properties) throws Exception {

        final Map<String, List<String>> graph = new HashMap<>();

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(properties.getCountriesMap(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Country> countries = mapper.readValue(jsonResponse, new TypeReference<>() {});

        for (Country c : countries) {

            graph.put(c.cca3(), c.borders() != null ? c.borders() : new ArrayList<>());
        }

        return graph;
    }
}
