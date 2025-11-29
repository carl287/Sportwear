package com.example.Sportwear.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {

    private final String API_URL =
            "https://api.fxratesapi.com/latest?base=CLP&symbols=USD,EUR";


    private final double USD_RATE = 0.0011;
    private final double EUR_RATE = 0.00093;

    public double convertCLP(int amount, String toCurrency) {


        RestTemplate restTemplate = new RestTemplate();
        try {
            Map response = restTemplate.getForObject(API_URL, Map.class);

            System.out.println("Respuesta de fxratesapi: " + response);
        } catch (Exception e) {

            System.out.println("No se pudo contactar la API externa, usando tasas fijas. Error: " + e.getMessage());
        }

        // Normalizamos la moneda
        String currencyKey = toCurrency.toUpperCase();


        switch (currencyKey) {
            case "USD":
                return amount * USD_RATE;

            case "EUR":
                return amount * EUR_RATE;

            default:
                throw new IllegalArgumentException("Moneda no soportada: " + toCurrency);
        }
    }
}
