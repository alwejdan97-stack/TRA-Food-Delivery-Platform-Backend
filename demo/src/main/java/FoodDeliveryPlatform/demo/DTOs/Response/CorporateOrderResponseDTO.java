package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.ComboMealDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.CorporateOrderDTOSummary;
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
    private double costCenter;
    private LocalDate orderDate;
    @Pattern(regexp = "PENDING|...| CANCELLED |...| DELIVERED")
    private String status;
    @DecimalMin("0.0")
    private double totalAmount;
    private CorporateOrderDTOSummary corporateOrderDTOSummary;

    public static CorporateOrderResponseDTO convertToDTO(CorporateOrder entity) {
        CorporateOrderResponseDTO dto = new CorporateOrderResponseDTO();
        dto.setId(entity.getId());
        dto.setCorporateCode(entity.getCorporateCode());
        dto.setCompanyName(entity.getCompanyName());
        dto.setCostCenter(entity.getCostCenter());
        dto.setOrderDate(entity.getOrderDate());
        dto.setStatus(entity.getStatus());
        dto.setTotalAmount(entity.getTotalAmount());

        CorporateOrderDTOSummary summary=new CorporateOrderDTOSummary();
        summary.setOrderDate(entity.getOrderDate());
        summary.setCorporateCode(entity.getCorporateCode());
        summary.setCostCenter(entity.getCostCenter());
        summary.setCompanyName(entity.getCompanyName());
        summary.setTotalAmount(entity.getTotalAmount());

        dto.setCorporateOrderDTOSummary(summary);
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
