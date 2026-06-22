package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDriver extends BaseEntity{
    private String firstName;
    private String lastName;

    @Column(unique = true,nullable = true)
    private String email;

    private String phone;
    private String passwordHash;
    private String driverCode;
    private String vehicleType;
    private String vehiclePlate;
    private Double currentLat;
    private Double currentLng;
    private Boolean isOnline;

    @OneToMany()
    private List<Delivery> deliveries;
}
