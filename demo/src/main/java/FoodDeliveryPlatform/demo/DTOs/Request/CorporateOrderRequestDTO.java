package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.CorporateOrder;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CorporateOrderRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Code Can't Be Empty")
    private String corporateCode;
    @NotBlank(message = "Name Can't Be Empty")
    private String companyName;
    @DecimalMin("0.0")
    private double costCenter;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private String status;
    private LocalDate orderDate;
    @DecimalMin("0.0")
    private double totalAmount;

    public void applyToEntity(CorporateOrder entity){
        entity.setCorporateCode(this.corporateCode);
        entity.setId(this.id);
        entity.setCompanyName(this.companyName);
        entity.setCostCenter(this.costCenter);
        entity.setStatus(this.status);
        entity.setOrderDate(this.orderDate);
        entity.setTotalAmount(this.totalAmount);
    }
}
