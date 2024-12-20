package br.com.toecommerce.demo.Services;

import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.utils.FailureSimulator;

@Service
public class FidelityService {
    
    public void registerBonus(String userId, int bonus, boolean ft) throws Exception {
        
        
        FailureSimulator.simulateFail("time", 0.1, 0);
        
        System.out.println("Bônus de " + bonus + " pontos registrado para o usuário " + userId);
        System.out.println("O ft é: " + ft);
    }
}
