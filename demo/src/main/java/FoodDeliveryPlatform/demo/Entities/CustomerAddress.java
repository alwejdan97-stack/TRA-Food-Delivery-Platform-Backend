package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddress extends BaseEntity{
    private String street;
    private String city;
    private String building;
    private boolean isDefault;

    @ManyToOne
    private Customer customer;
}
