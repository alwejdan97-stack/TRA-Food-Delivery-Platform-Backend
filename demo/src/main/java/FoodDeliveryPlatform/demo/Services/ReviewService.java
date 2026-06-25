package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.ReviewResponseDTO;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import FoodDeliveryPlatform.demo.Repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    DeliveryRepository deliveryRepository;

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
        DeliveryDriver driver = deliveryRepository.findById(driverId).get();
        if(!driver.getIsActive()||driver==null){
            throw new ResourceNotFoundException(ErrorMessage.DRIVER_NOT_FOUND);
        }
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setRating(rating);
        responseDTO.setComment(comment);
        responseDTO.setComment("Customer " + customerId + " successfully reviewed driver " + driver.getId());

        deliveryRepository.save(driver);
        return responseDTO;
    }
}
