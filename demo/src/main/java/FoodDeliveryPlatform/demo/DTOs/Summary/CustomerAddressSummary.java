package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddressSummary {
    @NotNull
    private Integer id;
    @NotBlank(message = "Street Can't Be Null")
    private String street;
    @NotBlank(message = "City Can't Be Null")
    private String city;
    @NotBlank(message = "Building Can't Be Null")
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
