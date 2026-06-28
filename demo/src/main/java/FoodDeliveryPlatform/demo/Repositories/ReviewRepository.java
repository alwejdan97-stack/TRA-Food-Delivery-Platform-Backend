package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReviewRepository extends JpaRepository<Review,Integer> {
    @Query("SELECT COALESCE(AVG(R.rating), 0.0) FROM Review R WHERE R.restaurant.id = :restaurantId AND R.isActive = TRUE")
    Double getAverageRatingByRestaurantId(@Param("restaurantId") Integer restaurantId);

    @Query("SELECT COALESCE(AVG(R.rating), 0.0) FROM Review R WHERE R.deliveryDriver.id = :driverId AND R.isActive = TRUE")
    Double getAverageRatingByDriverId(@Param("driverId") Integer driverId);

    Page<Review> findByRestaurantIdAndIsActiveTrue(Integer restaurantId, Pageable pageable);
}
