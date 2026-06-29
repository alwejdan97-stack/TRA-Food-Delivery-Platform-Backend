package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerPatchDTO {
    private String phone;
    private String address;

    public static CustomerPatchDTO convertToDTO(Customer entity) {
        CustomerPatchDTO dto=new CustomerPatchDTO();
        dto.setPhone(entity.getPhone());
        dto.setAddress(dto.getAddress());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<CustomerPatchDTO> convertToDTO(List<Customer> entities) {
        List<CustomerPatchDTO> dtos = new ArrayList<>();
        for (Customer entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }

}
