package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    @Query("SELECT R FROM Restaurant R WHERE LOWER(R.cuisineType)=LOWER(:cuisineType) AND R.isActive=TRUE")
    List<Restaurant> findByCuisineTypeIgnoreCase(@Param("cuisineType") String cuisineType);

    @Query("SELECT R FROM Restaurant R WHERE R.acceptingOrders= TRUE AND R.isActive=TRUE")
    List<Restaurant> findByAcceptingOrdersTrue();

    @Query("SELECT R FROM Restaurant R WHERE R.deliveryFee<= :deliveryFee AND R.isActive=TRUE")
    List<Restaurant> findByDeliveryFeeLessThanEqual(@Param("deliveryFee") Double fee);

    @Query("SELECT R FROM Restaurant R WHERE R.restaurantOwner.id= :id AND R.isActive=TRUE AND R.restaurantOwner.isActive=TRUE")
    List<Restaurant> findByRestaurantOwnerId(@Param("id") Integer id);

    @Query("SELECT R FROM Restaurant R WHERE R.name LIKE CONCAT('%',:keyword,'%') AND R.isActive=TRUE")
    List<Restaurant> findRestaurantsByNameKeyword(@Param("keyword") String keyWord);
}
