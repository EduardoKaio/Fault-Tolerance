package br.com.toecommerce.demo.Models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    private double rate; // Taxa de câmbio
}
