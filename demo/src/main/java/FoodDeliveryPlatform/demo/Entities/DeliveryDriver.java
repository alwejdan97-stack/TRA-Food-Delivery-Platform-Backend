package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deliveryDriver")
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

    @OneToMany(mappedBy = "deliveryDriver")
    private List<Delivery> deliveries;
}
