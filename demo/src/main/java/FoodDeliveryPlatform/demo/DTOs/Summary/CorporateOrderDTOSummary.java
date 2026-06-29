package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.CorporateOrder;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CorporateOrderDTOSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Code Can't Be Empty")
    private String corporateCode;
    @NotBlank(message = "Name Can't Be Empty")
    private String companyName;
    @DecimalMin("0.0")
    private double costCenter;
    private LocalDate orderDate;
    @DecimalMin("0.0")
    private double totalAmount;

    public static CorporateOrderDTOSummary fromEntity(CorporateOrder entity) {
        CorporateOrderDTOSummary dto = new CorporateOrderDTOSummary();
        dto.setId(entity.getId());
        dto.setCorporateCode(entity.getCorporateCode());
        dto.setCompanyName(entity.getCompanyName());
        dto.setCostCenter(entity.getCostCenter());
        dto.setOrderDate(entity.getOrderDate());
        dto.setTotalAmount(entity.getTotalAmount());
        return dto;
    }
}
