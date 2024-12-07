package br.com.toecommerce.demo.Models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String id;
    private String productId;
    private LocalDateTime timestamp;

}
