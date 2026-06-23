package FoodDeliveryPlatform.demo.DTOs.Response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeliveryDTOResponse {
    @NotNull
    private Integer id;
    @NotBlank(message = "Code Can't Be Empty")
    private String trackingCode;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private Boolean status;
    private LocalDateTime assignedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;
}
