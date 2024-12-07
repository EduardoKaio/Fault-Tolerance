package br.com.toecommerce.demo.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.Models.Product;
import br.com.toecommerce.demo.utils.FailureSimulator;

@Service
public class StoreService {

private final Map<String, Product> products = new HashMap<>();

    public StoreService() {
        // Inicializa três produtos diferentes
        products.put("1", new Product("1", "Smart TV", 500.0));
        products.put("2", new Product("2", "Alexa", 50.0));
        products.put("3", new Product("3", "Iphone 16", 1000.0));
    }

    public Product getProductDetails(String productId) throws Exception {
        
        FailureSimulator.simulateFail("omission", 0.2, 0);
        
        // Retorna os detalhes do produto pelo ID
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + productId);
        }
        return product;
    }

    public String sellProduct(String productId) {
        // Simula o registro de uma venda
        if (!products.containsKey(productId)) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + productId);
        }
        
        return UUID.randomUUID().toString();
    }
}
