package br.com.toecommerce.demo.utils;

import java.util.Random;

public class FailureSimulator {

    private static final Random random = new Random();
    private static long lastFailureTime = 0;  // Para controlar o intervalo da falha de tipo Time

    public static void simulateFail(String type, double probability, int durationMs) throws Exception {
        if (random.nextDouble() <= probability) { // Simula probabilidade
            switch (type.toLowerCase()) {
                case "omission":
                    System.out.println("Falha de Omissão Simulada.");
                    throw new RuntimeException("Falha de Omissão: Nenhuma resposta será retornada.");

                case "error":
                    System.out.println("Falha de Erro Simulada.");
                    Thread.sleep(durationMs); // Simula a duração do erro
                    throw new RuntimeException("Falha de Erro: Dados inválidos ou erro na resposta.");

                case "crash":
                    System.out.println("Falha de Crash Simulada.");
                    Thread.sleep(durationMs); // Simula crash antes de parar
                    System.exit(1); // Finaliza o processo simulando um crash
                    break;

                case "time":
                    // Controlando a falha de "Time" para ocorrer apenas por 30 segundos.
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastFailureTime > 30000) {
                        lastFailureTime = currentTime;  // Atualiza a última falha de time

                        System.out.println("Falha de Timeout Simulada.");
                        Thread.sleep(2000); // Simula o delay de 2 segundos
                    } else {
                        System.out.println("Falha de Timeout ignorada, fora do intervalo de 30s.");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Tipo de falha desconhecido: " + type);
            }
        }
    }
}

