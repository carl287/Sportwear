package com.example.Sportwear.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {

    private final String API_URL =
            "https://api.fxratesapi.com/latest?base=CLP&symbols=USD,EUR";

    public double convertCLP(int amount, String toCurrency) {

        RestTemplate restTemplate = new RestTemplate();

        // Llamar a la API externa
        Map response = restTemplate.getForObject(API_URL, Map.class);

        if (response == null || !response.containsKey("rates")) {
            throw new IllegalStateException("No se pudo obtener las tasas desde la API externa.");
        }

        // Obtener el objeto "rates"
        Map<String, Object> rates = (Map<String, Object>) response.get("rates");

        // Normalizamos la moneda solicitada
        String currencyKey = toCurrency.toUpperCase();

        if (!rates.containsKey(currencyKey)) {
            throw new IllegalArgumentException("Moneda no soportada: " + toCurrency);
        }

        // Convertimos la tasa a double
        double rate = Double.parseDouble(rates.get(currencyKey).toString());

        // Retornamos la conversión
        return amount * rate;
    }
}
