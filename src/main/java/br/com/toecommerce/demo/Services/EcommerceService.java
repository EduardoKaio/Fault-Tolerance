package br.com.toecommerce.demo.Services;

import org.springframework.stereotype.Service;

import br.com.toecommerce.demo.Models.BuyRequest;
import br.com.toecommerce.demo.Models.Product;

@Service
public class EcommerceService {
    
    private final StoreService storeService;
    private final ExchangeService exchangeService;
    private final FidelityService fidelityService;

    public EcommerceService(StoreService storeService, ExchangeService exchangeService, FidelityService fidelityService) {
        this.storeService = storeService;
        this.exchangeService = exchangeService;
        this.fidelityService = fidelityService;
    }

    // @CircuitBreaker(name = "exchange", fallbackMethod = "fallbackExchangeRate")
    // @Retry(name = "exchange")
    public String processBuy(BuyRequest buyRequest) throws Exception {
        // Obtém detalhes do produto
        Product product = storeService.getProductDetails(buyRequest.getProduct());

        // Obtém a taxa de câmbio
        double exchangeRate = exchangeService.getExchangeRate();

        // Calcula o valor em moeda local
        double valueInReal = product.getValue() * exchangeRate;

        // Registra a venda no Store
        String transactionId = storeService.sellProduct(buyRequest.getProduct());

        int bonus = (int) Math.round(product.getValue());
        
        // Registra bônus no Fidelity
        fidelityService.registerBonus(buyRequest.getUser(), bonus);
        
        System.out.println("O valor gasto pelo usuário foi: " + valueInReal + "reais");
        return transactionId;
    }

    // Método de fallback para a falha no Exchange
    // public String fallbackExchangeRate(BuyRequest buyRequest, Throwable t) {
    //     return "Erro ao obter taxa de câmbio: usando valor padrão de 1.0.";
    // }
}
