package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class CorporateOrder extends BaseEntity{
    private String corporateCode;
    private String companyName;
    private Double costCenter;
    private LocalDate orderDate;
    private Boolean status;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name="restaurantID")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "corporateOrder")
    private List<OrderItem> orderItems;
}
