package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Entities.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDTORequest {
    @NotBlank(message = "Target Can't Be Empty")
    private String targetType;
    @Min(1) @Max(5)
    private Integer rating;
    @NotBlank(message = "Comment Can't Be Empty")
    private String comment;

    public void applyToEntity(Review entity){
        entity.setTargetType(this.targetType);
        entity.setRating(this.rating);
        entity.setComment(this.comment);
    }
}
