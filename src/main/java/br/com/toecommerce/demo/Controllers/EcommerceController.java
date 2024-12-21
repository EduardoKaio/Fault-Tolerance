package br.com.toecommerce.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.toecommerce.demo.Models.FaultTolerance;
import br.com.toecommerce.demo.Services.EcommerceService;

@RestController
public class EcommerceController {
    
    @Autowired
    private final EcommerceService ecommerceService;

    @Autowired
    private final FaultTolerance faultTolerance;
    private final RestTemplate restTemplate = new RestTemplate();


    @Value("${store.service.url}")
    private String storeServiceUrl;

    @Value("${exchange.service.url}")
    private String exchangeServiceUrl;

    @Value("${fidelity.service.url}")
    private String fidelityServiceUrl;
    
    public EcommerceController(EcommerceService ecommerceService, FaultTolerance faultTolerance) {
        this.ecommerceService = ecommerceService;
        this.faultTolerance = faultTolerance;
    }

    @GetMapping("/")
    public String home() throws InterruptedException {
        Thread.sleep(1000);
        return "Aplicação funcionando!";
    }

    // Endpoint /buy, com parâmetros na URL
    @GetMapping("/buy")
    public ResponseEntity<?> buyProduct(
        @RequestParam("product") String productId,
        @RequestParam("user") String userId,
        @RequestParam("ft") boolean ft) throws InterruptedException {
    try {
        Thread.sleep(1000);
        System.out.println("ft no eccomerceController" + ft);
        // Passo 1: Obter detalhes do produto via Store
        String productUrl = storeServiceUrl + "/product?product=" + productId + "&ft=" + ft;
        var productDetails = restTemplate.getForObject(productUrl, String.class);

        // Passo 2: Obter taxa de câmbio via Exchange
        String exchangeUrl = exchangeServiceUrl + "/exchange?" + "ft=" + ft;
        double exchangeRate = restTemplate.getForObject(exchangeUrl, Double.class);

        // Passo 3: Registrar venda via Store
        String sellUrl = storeServiceUrl + "/sell?product=" + productId + "&ft=" + ft;
        String transactionId = restTemplate.postForObject(sellUrl, null, String.class);

        // Passo 4: Registrar bônus via Fidelity
        String bonusUrl = fidelityServiceUrl + "/bonus?user=" + userId + "&bonus=" + Math.round(100) + "&ft=" + ft;
        restTemplate.postForEntity(bonusUrl, null, Void.class);

        // Resposta final
        return ResponseEntity.ok("Compra realizada com sucesso. ID da transação: " + transactionId);
    } catch (RestClientException e) {
        return ResponseEntity.status(500).body("Erro ao processar compra: " + e.getMessage());
    }
    }
}
