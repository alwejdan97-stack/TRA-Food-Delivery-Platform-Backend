package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerAddressResponseDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "Street Can't Be Null")
    private String street;
    @NotBlank(message = "City Can't Be Null")
    private String city;
    @NotBlank(message = "Building Can't Be Null")
    private String building;

    public static CustomerAddressResponseDTO convertToDTO(CustomerAddress entity) {
        CustomerAddressResponseDTO dto = new CustomerAddressResponseDTO();
        dto.setId(entity.getId());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        dto.setBuilding(entity.getBuilding());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<CustomerAddressResponseDTO> convertToDTO(List<CustomerAddress> entities) {
        List<CustomerAddressResponseDTO> dtos = new ArrayList<>();
        for (CustomerAddress entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
