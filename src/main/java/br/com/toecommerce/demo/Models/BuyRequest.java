package br.com.toecommerce.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyRequest {
    private String product;  // ID do produto a ser comprado
    private String user;     // ID do usuário que está executando a compra
    private boolean ft;      // Flag para indicar se a tolerância a falhas está ativada
}
