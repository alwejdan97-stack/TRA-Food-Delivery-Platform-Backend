package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddressSummary {
    private Integer id;
    private String street;
    private String city;
    private String building;

    public static CustomerAddressSummary fromEntity(CustomerAddress entity) {
        CustomerAddressSummary dto = new CustomerAddressSummary();
        dto.setId(entity.getId());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        dto.setBuilding(entity.getBuilding());
        return dto;
    }
}
