package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Delivery extends BaseEntity{
    private String trackingCode;
    private Boolean status;
    private LocalDateTime assignedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "deliveryDriverID")
    private DeliveryDriver deliveryDriver;
}
