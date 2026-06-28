package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Response.ReviewResponseDTO;
import FoodDeliveryPlatform.demo.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping("/leaveRestaurantReview/restaurant/{restaurantId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> leaveRestaurantReview(@PathVariable Integer restaurantId, @PathVariable Integer customerId, @RequestParam int rating, @RequestParam(required = false, defaultValue = "") String comment) {
        ReviewResponseDTO response = reviewService.leaveRestaurantReview(customerId, restaurantId, rating, comment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/leaveDriverReview/driver/{driverId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> leaveDriverReview(@PathVariable Integer driverId, @PathVariable Integer customerId, @RequestParam int rating, @RequestParam(required = false, defaultValue = "") String comment) {
        ReviewResponseDTO response = reviewService.leaveDriverReview(customerId, driverId, rating, comment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getReviewsForRestaurant/restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsForRestaurant(@PathVariable Integer restaurantId) {
        List<ReviewResponseDTO> mockList = new ArrayList<>();
        return ResponseEntity.ok(mockList);
    }

    @GetMapping("/getReviewsForDriver/driver/{driverId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsForDriver(@PathVariable Integer driverId) {
        List<ReviewResponseDTO> mockList = new ArrayList<>();
        return ResponseEntity.ok(mockList);
    }

    @DeleteMapping("deleteReview/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getRestaurantAverageRating/restaurant/{restaurantId}/average")
    public ResponseEntity<Double> getRestaurantAverageRating(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(reviewService.getAverageRestaurantRating(restaurantId));
    }

    @GetMapping("/getDriverAverageRating/driver/{driverId}/average")
    public ResponseEntity<Double> getDriverAverageRating(@PathVariable Integer driverId) {
        return ResponseEntity.ok(reviewService.getAverageDriverRating(driverId));
    }

    @GetMapping("/getPaginatedReviews/restaurant/{restaurantId}")
    public ResponseEntity<Page<ReviewResponseDTO>> getPaginatedReviews(@PathVariable Integer restaurantId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(reviewService.getPaginatedRestaurantReviews(restaurantId, page, size));
    }
}
