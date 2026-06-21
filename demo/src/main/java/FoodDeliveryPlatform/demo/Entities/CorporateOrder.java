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
@Table(name = "CorporateOrder")
public class CorporateOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String corporateCode;
    private String companyName;
    private double costCenter;
    private LocalDate orderDate;
    private Boolean status;
    private double totalAmount;

    @ManyToOne
    @Column(name="restaurantID")
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "CorporateOrder")
    private List<OrderItem> orderItems;
}
