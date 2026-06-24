package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Delivery extends BaseEntity{
    private String trackingCode;
    private Boolean status;
    private LocalDateTime assignedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;

    @OneToOne()
    private Orders orders;

    @ManyToOne
    @JoinColumn()
    private DeliveryDriver deliveryDriver;
}
