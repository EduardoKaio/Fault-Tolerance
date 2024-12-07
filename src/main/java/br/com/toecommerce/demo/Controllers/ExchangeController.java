// package br.com.toecommerce.demo.Controllers;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import br.com.toecommerce.demo.Services.ExchangeService;

// @RestController
// @RequestMapping("/exchange")
// public class ExchangeController {

//     private final ExchangeService exchangeService;

//     public ExchangeController(ExchangeService exchangeService) {
//         this.exchangeService = exchangeService;
//     }

//     // Endpoint /exchange (GET)
//     @GetMapping
//     public ResponseEntity<?> getExchangeRate() throws InterruptedException {
//         Thread.sleep(1000);
//         try {
//             double exchangeRate = exchangeService.getExchangeRate();
//             return ResponseEntity.ok(exchangeRate);
//         } catch (Exception e) {
//             return ResponseEntity.status(500).body("Erro ao obter taxa de câmbio: " + e.getMessage());
//         }
//     }
// }