package br.com.toecommerce.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.toecommerce.demo.Models.BuyRequest;
import br.com.toecommerce.demo.Services.EcommerceService;

@RestController
public class EcommerceController {
    
    @Autowired
    private final EcommerceService ecommerceService;

    public EcommerceController(EcommerceService ecommerceService) {
        this.ecommerceService = ecommerceService;
    }

    @GetMapping("/")
    public String home() throws InterruptedException {
        Thread.sleep(1000);
        return "Aplicação funcionando!";
    }

    // Endpoint /buy, com parâmetros na URL
    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct (@RequestParam("product") String productId, 
        @RequestParam("user") String userId, @RequestParam("ft") boolean ft) throws InterruptedException {
        
        Thread.sleep(1000);
        try {
            // Criando o objeto BuyRequest a partir dos parâmetros da URL
            BuyRequest buyRequest = new BuyRequest(productId, userId, ft);
            
            // Processando a compra
            String transactionId = ecommerceService.processBuy(buyRequest);
            
            return ResponseEntity.ok().body("Compra realizada com sucesso. ID da transação: " + transactionId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar compra: " + e.getMessage());
        }
    }
}
