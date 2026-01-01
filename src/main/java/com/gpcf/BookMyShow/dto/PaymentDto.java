package com.gpcf.BookMyShow.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;
    private String transactionId;
    private Double amount;
    private LocalDateTime paymentTime;
    private String paymentMethod; // CARD, UPI
    private String status;        // SUCCESS, FAILED
}
