package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.RestaurantDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.ReviewDTOSummary;
import FoodDeliveryPlatform.demo.Entities.Review;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ReviewResponseDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Target Can't Be Empty")
    private String targetType;
    @Min(1) @Max(5)
    private int rating;
    @NotBlank(message = "Comment Can't Be Empty")
    private String comment;
    private ReviewDTOSummary reviewDTOSummary;

    public static ReviewResponseDTO convertToDTO(Review entity) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setTargetType(entity.getTargetType());
        dto.setRating(entity.getRating());

        ReviewDTOSummary summary=new ReviewDTOSummary();
        summary.setId(entity.getId());
        summary.setComment(entity.getComment());
        summary.setRating(entity.getRating());
        summary.setTargetType(entity.getTargetType());

        dto.setReviewDTOSummary(summary);
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<ReviewResponseDTO> convertToDTO(List<Review> entities) {
        List<ReviewResponseDTO> dtos = new ArrayList<>();
        for (Review entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
