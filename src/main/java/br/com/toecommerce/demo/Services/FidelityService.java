package br.com.toecommerce.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.Models.FaultTolerance;
import br.com.toecommerce.demo.utils.FailureSimulator;

@Service
public class FidelityService {
    private final FaultTolerance faultTolerance;
    
    @Autowired
    public FidelityService(FaultTolerance faultTolerance) {
        this.faultTolerance = faultTolerance;
    }
    public void registerBonus(String userId, int bonus) throws Exception {
        boolean ft = faultTolerance.isFaultTolerance();
        
        FailureSimulator.simulateFail("time", 0.1, 0);
        
        System.out.println("Bônus de " + bonus + " pontos registrado para o usuário " + userId);
        System.out.println("O ft é: " + ft);
    }
}
