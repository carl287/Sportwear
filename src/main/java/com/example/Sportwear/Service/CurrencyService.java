package com.example.Sportwear.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {

    private final String API_URL = "https://open.er-api.com/v6/latest/CLP";

    public double convertCLP(int amount, String toCurrency) {

        RestTemplate restTemplate = new RestTemplate();

        // Llamada simple a la API
        Map response = restTemplate.getForObject(API_URL, Map.class);

        // Obtenemos el mapa de tasas
        Map<String, Object> rates = (Map<String, Object>) response.get("rates");

        // Extraemos la tasa según la moneda
        Object rawRate = rates.get(toCurrency.toUpperCase());
        if (rawRate == null) {
            throw new IllegalArgumentException("Moneda no soportada: " + toCurrency);
        }

        // Convertimos a double
        double rate = Double.parseDouble(rawRate.toString());

        // Multiplicamos el precio CLP por la tasa
        return amount * rate;
    }
}
