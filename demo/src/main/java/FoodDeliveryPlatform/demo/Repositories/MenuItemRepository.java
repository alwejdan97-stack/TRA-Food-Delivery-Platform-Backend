package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {
    @Query("SELECT M FROM MenuItem M WHERE M.restaurant.id= :id AND M.isActive=TRUE AND M.restaurant.isActive=TRUE")
    List<MenuItem>  findByRestaurantId(@Param("id") Integer id);

    @Query("SELECT M FROM MenuItem M WHERE M.restaurant.id= :id AND M.isAvailable=TRUE AND M.isActive=TRUE AND M.restaurant.isActive=TRUE")
    List<MenuItem> findByRestaurantIdAndIsAvailableTrue(@Param("id") Integer id);

    @Query("SELECT M FROM MenuItem M WHERE M.isVegetarian= TRUE AND M.isActive=TRUE")
    List<MenuItem> findByIsVegetarianTrue();

    @Query("SELECT M FROM MenuItem M WHERE M.price BETWEEN :min AND :max AND M.isActive=TRUE")
    List<MenuItem> findByPriceBetween(@Param("min") Double min, @Param("max") Double max);

    @Query("SELECT M FROM MenuItem M WHERE M.id= :menuItemId AND M.isActive=TRUE")
    List<MenuItem> findByMenuItemId(@Param("menuItemId") Integer menuItemId);

    @Query("SELECT M FROM MenuItem M WHERE LOWER(M.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND (:minCal IS NULL OR M.calories >= :minCal) AND (:maxCal IS NULL OR M.calories <= :maxCal) " + "AND M.isActive = TRUE")
    List<MenuItem> searchCrossRestaurantMenu(@Param("keyword") String keyword, @Param("minCal") Integer minCal, @Param("maxCal") Integer maxCal);
}
