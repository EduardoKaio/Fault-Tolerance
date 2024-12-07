package br.com.toecommerce.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FidelityBonus {
    private String user;  // ID do usuário
    private int bonus;    // Quantidade de bônus
}