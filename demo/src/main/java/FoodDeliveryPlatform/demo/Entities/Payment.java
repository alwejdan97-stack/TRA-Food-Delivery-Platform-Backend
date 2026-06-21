package FoodDeliveryPlatform.demo.Entities;

import java.time.LocalDateTime;

public class Payment {
    private long id;
    private String paymentMethod;
    private String status;
    private double amount;
    private String transactionRef;
    private LocalDateTime processedAt;
}
