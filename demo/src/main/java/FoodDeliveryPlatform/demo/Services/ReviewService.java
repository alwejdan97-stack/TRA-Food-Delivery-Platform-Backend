package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.ReviewResponseDTO;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Entities.Review;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.DeliveryDriverRepository;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import FoodDeliveryPlatform.demo.Repositories.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    DeliveryRepository deliveryRepository;
    DeliveryDriverRepository driverRepository;

    public ReviewService(ReviewRepository reviewRepository, DeliveryRepository deliveryRepository) {
        this.reviewRepository = reviewRepository;
        this.deliveryRepository = deliveryRepository;
    }

    public ReviewResponseDTO leaveRestaurantReview(Integer customerId, Integer restaurantId, int rating, String comment){
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RATING);
        }
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setRating(rating);
        responseDTO.setComment(comment);
        responseDTO.setComment("Customer " + customerId + " successfully reviewed restaurant " + restaurantId);

        return responseDTO;
    }

    public ReviewResponseDTO leaveDriverReview(Integer customerId, Integer driverId, int rating, String comment){
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RATING);
        }
        DeliveryDriver driver = driverRepository.findById(driverId).get();
        if(!driver.getIsActive()||driver==null){
            throw new ResourceNotFoundException(ErrorMessage.DRIVER_NOT_FOUND);
        }
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setRating(rating);
        responseDTO.setComment(comment);
        responseDTO.setComment("Customer " + customerId + " successfully reviewed driver " + driver.getId());

        driverRepository.save(driver);
        return responseDTO;
    }

    public Double getAverageRestaurantRating(Integer restaurantId) {
        return reviewRepository.getAverageRatingByRestaurantId(restaurantId);
    }

    public Double getAverageDriverRating(Integer driverId) {
        return reviewRepository.getAverageRatingByDriverId(driverId);
    }

    public Page<ReviewResponseDTO> getPaginatedRestaurantReviews(Integer restaurantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewRepository.findByRestaurantIdAndIsActiveTrue(restaurantId, pageable);

        List<ReviewResponseDTO> dtos = new ArrayList<>();
        for (Review r : reviewPage.getContent()) {
            dtos.add(ReviewResponseDTO.convertToDTO(r));
        }
        return new PageImpl<>(dtos, pageable, reviewPage.getTotalElements());
    }
}
