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
@Table(name = "DeliveryDriver")
public class DeliveryDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driverId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;
    private String driverCode;
    private String vehicleType;
    private String vehiclePlate;
    private double currentLat;
    private double currentLng;
    private Boolean isOnline;

    @OneToMany(mappedBy = "DeliveryDriver")
    private List<Delivery> deliveries;
}
