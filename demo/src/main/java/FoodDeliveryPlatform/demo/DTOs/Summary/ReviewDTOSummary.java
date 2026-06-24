package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;
import FoodDeliveryPlatform.demo.Entities.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDTOSummary {
    @NotBlank(message = "Target Can't Be Empty")
    private String targetType;
    @Min(1) @Max(5)
    private int rating;
    @NotBlank(message = "Comment Can't Be Empty")
    private String comment;

    public static ReviewDTOSummary fromEntity(Review entity) {
        ReviewDTOSummary dto = new ReviewDTOSummary();
        dto.setComment(entity.getComment());
        dto.setTargetType(entity.getTargetType());
        dto.setRating(entity.getRating());
        return dto;
    }
}
