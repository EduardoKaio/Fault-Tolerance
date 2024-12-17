package br.com.toecommerce.demo.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.Models.FaultTolerance;
import br.com.toecommerce.demo.Models.Product;
import br.com.toecommerce.demo.utils.FailureSimulator;

@Service
public class StoreService {

    private final Map<String, Product> products = new HashMap<>();
    private final FaultTolerance faultTolerance;
    
    public StoreService(FaultTolerance faultTolerance) {
        // Inicializa três produtos diferentes
        products.put("1", new Product("1", "Smart TV", 500.0));
        products.put("2", new Product("2", "Alexa", 50.0));
        products.put("3", new Product("3", "Iphone 16", 1000.0));
        this.faultTolerance = faultTolerance;
    }

    public Product getProductDetails(String productId) throws Exception {
        boolean ft = faultTolerance.isFaultTolerance();
        System.out.println("ft no storeservice" + ft);

        FailureSimulator.simulateFail("omission", 0.2, 0);
        
        // Retorna os detalhes do produto pelo ID
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + productId);
        }
        return product;
    }

    public String sellProduct(String productId) throws Exception {
        // Simula o registro de uma venda

        FailureSimulator.simulateFail("error", 0.1, 5000);

        if (!products.containsKey(productId)) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + productId);
        }
        
        return UUID.randomUUID().toString();
    }
}
