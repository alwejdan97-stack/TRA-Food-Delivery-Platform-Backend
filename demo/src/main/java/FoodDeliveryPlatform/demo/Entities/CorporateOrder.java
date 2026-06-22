package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class CorporateOrder extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
