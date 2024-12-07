// package br.com.toecommerce.demo.Controllers;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import br.com.toecommerce.demo.Models.Product;
// import br.com.toecommerce.demo.Services.StoreService;

// @RestController
// @RequestMapping("/store")
// public class StoreController {

//     private final StoreService storeService;

//     public StoreController(StoreService storeService) {
//         this.storeService = storeService;
//     }

//     // Endpoint /product (GET)
//     @GetMapping("/product")
//     public ResponseEntity<?> getProduct(@RequestParam String product) throws InterruptedException {
//         Thread.sleep(1000);
//         try {
//             Product productDetails = storeService.getProductDetails(product);
//             return ResponseEntity.ok(productDetails);
//         } catch (Exception e) {
//             return ResponseEntity.status(404).body("Produto não encontrado: " + e.getMessage());
//         }
//     }

//     // Endpoint /sell (POST)
//     @PostMapping("/sell")
//     public ResponseEntity<?> sellProduct(@RequestParam String product) throws InterruptedException{
//         Thread.sleep(1000);
//         try {
//             String transactionId = storeService.sellProduct(product);
//             return ResponseEntity.ok("Venda realizada com sucesso. ID da transação: " + transactionId);
//         } catch (Exception e) {
//             return ResponseEntity.status(500).body("Erro ao processar venda: " + e.getMessage());
//         }
//     }
// }