package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.CorporateOrder;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CorporateOrderRequestDTO {
    @NotBlank(message = "Code Can't Be Empty")
    private String corporateCode;
    @NotBlank(message = "Name Can't Be Empty")
    private String companyName;
    @DecimalMin("0.0")
    private double costCenter;
    private LocalDate orderDate;
    @DecimalMin("0.0")
    private double totalAmount;

    public void applyToEntity(CorporateOrder entity){
        entity.setCorporateCode(this.corporateCode);
        entity.setCompanyName(this.companyName);
        entity.setCostCenter(this.costCenter);
        entity.setOrderDate(this.orderDate);
        entity.setTotalAmount(this.totalAmount);
    }
}
