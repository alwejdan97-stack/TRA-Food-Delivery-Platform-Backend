package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddressRequest {
    private Integer id;
    private String street;
    private String city;
    private String building;

    public void applyToEntity(CustomerAddress entity){
        entity.setId(this.getId());
        entity.setStreet(this.getStreet());
        entity.setCity(this.getCity());
        entity.setBuilding(this.getBuilding());
    }
}
