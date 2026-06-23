package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;

public class RestaurantOwnerSummary extends PersonDTO {
    public static RestaurantOwnerSummary fromEntity(RestaurantOwner entity) {
        RestaurantOwnerSummary dto = new RestaurantOwnerSummary();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }
}
