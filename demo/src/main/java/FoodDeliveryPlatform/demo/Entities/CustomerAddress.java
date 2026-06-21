package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String street;
    private String city;
    private String building;
    private Boolean isDefault;
}
