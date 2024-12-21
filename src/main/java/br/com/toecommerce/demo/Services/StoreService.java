package br.com.toecommerce.demo.Services;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.Models.FaultTolerance;
import br.com.toecommerce.demo.Models.Product;
import br.com.toecommerce.demo.utils.FailureSimulator;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.decorators.Decorators;


@Service
public class StoreService {

    private final Map<String, Product> products = new HashMap<>();

    private CircuitBreaker circuitBreaker;
    
    public StoreService(FaultTolerance faultTolerance) {
        // Inicializa três produtos diferentes
        products.put("1", new Product("1", "Smart TV", 500.0));
        products.put("2", new Product("2", "Alexa", 50.0));
        products.put("3", new Product("3", "Iphone 16", 1000.0));

        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50) // Limite de falhas para abrir o circuito (em %)
            .waitDurationInOpenState(Duration.ofSeconds(5)) // Tempo que o circuito fica aberto
            .permittedNumberOfCallsInHalfOpenState(3) // Número de tentativas em Half-Open
            .slidingWindowSize(10) // Janela de monitoramento de falhas (em número de chamadas)
            .build();

        this.circuitBreaker = CircuitBreaker.of("storeServiceCircuitBreaker", config);
    }

    public Product getProductDetails(String productId, boolean ft) throws Exception {
        System.out.println("ft no storeservice: " + ft);
        
        CircuitBreaker.State circuitBreakerState = circuitBreaker.getState();
        if (circuitBreakerState == CircuitBreaker.State.OPEN) {
            System.out.println("Circuito aberto: Tentando pegar produto do cache...");
            Product productFromCache = getProductFromCache(productId); // Tenta obter o produto do cache
            if (productFromCache != null) {
                System.out.println("Produto encontrado no cache.");
                return productFromCache;
            } else {
                System.out.println("Produto não encontrado no cache.");
                throw new IllegalStateException("Produto não encontrado e circuito está aberto.");
            }
        }

        // Decore o método com o Circuit Breaker
        Supplier<Product> productSupplier = () -> {
            try {
                FailureSimulator.simulateFail("omission", 1, 0);
            } catch (Exception e) {
                
                e.printStackTrace();
            }

            Product product = products.get(productId);
            if (product == null) {
                throw new IllegalArgumentException("Produto não encontrado com o ID: " + productId);
            }
            return product;
        };

        // Use o Circuit Breaker para proteger a lógica
        Supplier<Product> decoratedSupplier = Decorators.ofSupplier(productSupplier)
            .withCircuitBreaker(circuitBreaker)
            .decorate();

        try {
            return decoratedSupplier.get();
        } catch (Exception e) {
            System.err.println("Erro ao obter detalhes do produto: " + e.getMessage());
            throw e;
        }
    }
    // Método para tentar obter o produto do cache
    private Product getProductFromCache(String productId) {
        System.out.println("Verificando se o produto está no cache...");
        // Simula um cache simples: Aqui você consultaria um cache real
        // Para fins de demonstração, vamos retornar null para simular que o cache não tem o produto
        return null; // Retorne o produto do cache se encontrado, ou null caso contrário
    }



    public String sellProduct(String productId, boolean ft) throws Exception {
        // Simula o registro de uma venda

        FailureSimulator.simulateFail("error", 0.1, 5000);

        if (!products.containsKey(productId)) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + productId);
        }
        
        return UUID.randomUUID().toString();
    }
}

