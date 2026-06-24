package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.CorporateOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CorporateOrderResponseDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "Code Can't Be Empty")
    private String corporateCode;
    @NotBlank(message = "Name Can't Be Empty")
    private String companyName;
    @DecimalMin("0.0")
    private Double costCenter;
    private LocalDate orderDate;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private Boolean status;
    @DecimalMin("0.0")
    private Double totalAmount;

    public static CorporateOrderResponseDTO convertToDTO(CorporateOrder entity) {
        CorporateOrderResponseDTO dto = new CorporateOrderResponseDTO();
        dto.setId(entity.getId());
        dto.setCorporateCode(entity.getCorporateCode());
        dto.setCompanyName(entity.getCompanyName());
        dto.setCostCenter(entity.getCostCenter());
        dto.setOrderDate(entity.getOrderDate());
        dto.setStatus(entity.getStatus());
        dto.setTotalAmount(entity.getTotalAmount());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<CorporateOrderResponseDTO> convertToDTO(List<CorporateOrder> entities) {
        List<CorporateOrderResponseDTO> dtos = new ArrayList<>();
        for (CorporateOrder entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
