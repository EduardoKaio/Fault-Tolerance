package br.com.toecommerce.demo.Services;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.utils.FailureSimulator;

@Service
public class FidelityService {

    // Fila para armazenar requisições que falharam
    private final BlockingQueue<Map<String, Object>> retryQueue = new LinkedBlockingQueue<>();

    /**
     * Registra o bônus para o usuário. Em caso de falha, adiciona a requisição na fila.
     */
    public void registerBonus(String userId, int bonus, boolean ft) throws Exception {
        try {
            // Simula falha com 10% de chance
            FailureSimulator.simulateFail("time", 0.1, 0);

            // Sucesso na operação
            System.out.println("Bônus de " + bonus + " pontos registrado para o usuário " + userId);
            System.out.println("O ft é: " + ft);

            // Verifica se o sistema está operacional
            if (FailureSimulator.isSystemOperational() && !retryQueue.isEmpty()) {
                System.out.println("Sistema operacional. Processando logs pendentes...");
                processRetryQueue();            
            }

        } catch (Exception e) {
            // Registra falha e adiciona à fila para retry
            System.err.println("Falha ao registrar bônus para o usuário " + userId + ": " + e.getMessage());
            retryQueue.add(Map.of(
                "userId", userId,
                "bonus", bonus
            ));
        }
    }

    private void processRetryQueue() {
        // Processa os logs pendentes
        System.out.println("Processando registros pendentes...");
        while (!retryQueue.isEmpty()) {
            Map<String, Object> log = retryQueue.poll(); // Remove da fila
            System.out.println("Bônus de " + log.get("bonus") + " pontos registrado para o usuário " + log.get("userId"));
        }
    }

}
