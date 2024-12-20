package br.com.toecommerce.demo.Services;

import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.utils.FailureSimulator;

@Service
public class ExchangeService {

    // Armazena o último valor válido de taxa de câmbio
    private static double lastValidExchangeRate = 5.0; // Valor inicial padrão

    public double getExchangeRate(Boolean ft) throws Exception {
        try {
            // Simula falhas no serviço
            FailureSimulator.simulateFail("crash", 0.1, 0);

            // Nova taxa de câmbio obtida do sistema
            double exchangeRate = 5.2; // Exemplo de valor atualizado (pode vir de uma fonte real)
            
            // Atualiza o último valor válido
            lastValidExchangeRate = exchangeRate;

            return exchangeRate;
        } catch (Exception e) {
            if (ft) {
                // Retorna o último valor válido em caso de falha
                return lastValidExchangeRate;
            } else {
                // Relança a exceção se a tolerância a falhas não estiver habilitada
                throw new RuntimeException("Falha ao obter a taxa de câmbio", e);
            }
        }
    }
}