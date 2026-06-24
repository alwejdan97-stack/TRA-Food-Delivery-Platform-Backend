package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CorporateOrder extends BaseEntity{
    private String corporateCode;
    private String companyName;
    private double costCenter;
    private LocalDate orderDate;
    private boolean status;
    private double totalAmount;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany()
    private List<OrderItem> orderItems;
}
