package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Response.ReviewResponseDTO;
import FoodDeliveryPlatform.demo.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/restaurant/{restaurantId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> leaveRestaurantReview(@PathVariable Integer restaurantId, @PathVariable Integer customerId, @RequestParam int rating, @RequestParam(required = false, defaultValue = "") String comment) {
        ReviewResponseDTO response = reviewService.leaveRestaurantReview(customerId, restaurantId, rating, comment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/driver/{driverId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> leaveDriverReview(@PathVariable Integer driverId, @PathVariable Integer customerId, @RequestParam int rating, @RequestParam(required = false, defaultValue = "") String comment) {
        ReviewResponseDTO response = reviewService.leaveDriverReview(customerId, driverId, rating, comment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(" /restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsForRestaurant(@PathVariable Integer restaurantId) {
        List<ReviewResponseDTO> mockList = new ArrayList<>();
        return ResponseEntity.ok(mockList);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsForDriver(@PathVariable Integer driverId) {
        List<ReviewResponseDTO> mockList = new ArrayList<>();
        return ResponseEntity.ok(mockList);
    }

    @DeleteMapping(" /{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
        return ResponseEntity.noContent().build();
    }

}
