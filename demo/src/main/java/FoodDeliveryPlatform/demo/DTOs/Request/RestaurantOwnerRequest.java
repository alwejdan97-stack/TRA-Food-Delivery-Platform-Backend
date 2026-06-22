package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;

public class RestaurantOwnerRequest extends PersonDTO {
    public void applyToEntity(DeliveryDriver entity){
        entity.setId(this.getId());
        entity.setFirstName(this.getFirstName());
        entity.setLastName(this.getLastName());
        entity.setEmail(this.getEmail());
        entity.setPhone(this.getPhone());
    }
}
