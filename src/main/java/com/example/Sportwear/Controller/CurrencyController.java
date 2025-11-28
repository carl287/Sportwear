package com.example.Sportwear.Controller;



import com.example.Sportwear.Service.CurrencyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
@CrossOrigin("*")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    // http://localhost:8080/api/currency/convert?amount=10000&to=USD
    @GetMapping("/convert")
    public double convert(
            @RequestParam int amount,
            @RequestParam String to
    ) {
        return currencyService.convertCLP(amount, to);
    }
}
