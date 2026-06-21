package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String paymentMethod;
    private String status;
    private double amount;
    private String transactionRef;
    private LocalDateTime processedAt;

    @OneToOne
    private List<Order> orders;
}
