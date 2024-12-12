package br.com.toecommerce.demo.Controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.toecommerce.demo.Services.FidelityService;

@RestController
public class FidelityController {

    private final FidelityService fidelityService;

    public FidelityController(FidelityService fidelityService) {
        this.fidelityService = fidelityService;
    }

    // Endpoint /bonus (POST)
    @PostMapping("/bonus")
    public ResponseEntity<?> registerBonus(@RequestParam String user, @RequestParam int bonus) throws InterruptedException {
        Thread.sleep(1000);
        try {
            fidelityService.registerBonus(user, bonus);
            System.out.println("Entrei no /bonus");
            return ResponseEntity.ok("Bônus registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao registrar bônus: " + e.getMessage());
        }
    }
}

