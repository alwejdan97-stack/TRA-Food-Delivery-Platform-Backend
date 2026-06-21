package FoodDeliveryPlatform.demo.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Delivery {
    private long id;
    private String trackingCode;
    private Boolean status;
    private LocalDateTime assignedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;
}
