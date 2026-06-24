package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequestDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "Target Can't Be Empty")
    private String targetType;
    @Min(1) @Max(5)
    private int rating;
    @NotBlank(message = "Comment Can't Be Empty")
    private String comment;

    public void applyToEntity(Review entity){
        entity.setId(this.getId());
        entity.setTargetType(this.targetType);
        entity.setRating(this.rating);
        entity.setComment(this.comment);
    }
}
