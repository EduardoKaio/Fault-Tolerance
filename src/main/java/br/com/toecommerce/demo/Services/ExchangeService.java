package br.com.toecommerce.demo.Services;

import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.utils.FailureSimulator;

@Service
public class ExchangeService {

    public double getExchangeRate() throws Exception {
        
        FailureSimulator.simulateFail("crash", 0.1, 0);
        
        return 5.0; 
    }
}