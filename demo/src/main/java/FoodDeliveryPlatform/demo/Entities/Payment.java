package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity{
    private String paymentMethod;
    private String status;
    private Double amount;
    private String transactionRef;
    private LocalDateTime processedAt;

    @OneToOne()
    private Orders orders;
}
