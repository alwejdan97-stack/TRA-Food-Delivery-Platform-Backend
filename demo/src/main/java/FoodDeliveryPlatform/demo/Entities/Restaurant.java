package FoodDeliveryPlatform.demo.Entities;

import java.time.LocalTime;

public class Restaurant {
    private long restaurantId;
    private String name;
    private String description;
    private String cuisineType;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private double minOrderAmount;
    private double deliveryFee;
    private Boolean acceptingOrders;
}
