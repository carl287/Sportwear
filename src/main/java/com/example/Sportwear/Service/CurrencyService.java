package com.example.Sportwear.Service;



import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {

    private final String API_URL = "https://api.frankfurter.app/latest?amount={amount}&from=CLP&to={to}";

    public double convertCLP(int amount, String toCurrency) {

        RestTemplate restTemplate = new RestTemplate();

        Map response = restTemplate.getForObject(
                API_URL,
                Map.class,
                amount,
                toCurrency
        );

        Map<String, Double> rates = (Map<String, Double>) response.get("rates");

        return rates.get(toCurrency);
    }
}

