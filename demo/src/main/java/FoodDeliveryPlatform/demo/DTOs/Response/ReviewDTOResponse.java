package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;
import FoodDeliveryPlatform.demo.Entities.Review;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ReviewDTOResponse {
    @NotNull
    private Integer id;
    @NotBlank(message = "Target Can't Be Empty")
    private String targetType;
    @Min(1) @Max(5)
    private Integer rating;
    @NotBlank(message = "Comment Can't Be Empty")
    private String comment;

    public static ReviewDTOResponse convertToDTO(Review entity) {
        ReviewDTOResponse dto = new ReviewDTOResponse();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setTargetType(entity.getTargetType());
        dto.setRating(entity.getRating());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<ReviewDTOResponse> convertToDTO(List<Review> entities) {
        List<ReviewDTOResponse> dtos = new ArrayList<>();
        for (Review entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
