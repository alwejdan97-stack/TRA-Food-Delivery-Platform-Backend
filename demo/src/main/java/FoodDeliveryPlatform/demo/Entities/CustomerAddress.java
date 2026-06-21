package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customerAddress")
public class CustomerAddress extends BaseEntity{
    private String street;
    private String city;
    private String building;
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
}
