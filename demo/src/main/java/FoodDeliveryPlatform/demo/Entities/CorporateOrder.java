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
    private Double costCenter;
    private LocalDate orderDate;
    private Boolean status;
    private Double totalAmount;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany()
    private List<OrderItem> orderItems;
}
