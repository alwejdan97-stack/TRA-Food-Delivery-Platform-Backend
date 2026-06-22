package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.ComboMeal;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import FoodDeliveryPlatform.demo.Entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComboMealRepository extends JpaRepository<ComboMeal,Integer> {
    @Query("SELECT CM FROM ComboMeal CM WHERE CM.restaurant.id= :id AND CM.isActive=TRUE AND CM.restaurant.isActive=TRUE")
    List<ComboMeal> findByRestaurantId(@Param("id") Integer id);

    @Query("SELECT CM FROM ComboMeal CM WHERE CM.restaurant.id= :id AND CM.isAvailable=TRUE AND CM.isActive=TRUE AND CM.restaurant.isActive=TRUE")
    List<ComboMeal> findByRestaurantIdAndIsAvailableTrue(@Param("id") Integer id);

    @Query("SELECT CM FROM ComboMeal CM JOIN CM.orderItems O WHERE O.isVegetarian= TRUE AND CM.isActive=TRUE AND O.isActive=TRUE")
    List<ComboMeal> findByIsVegetarianTrue();

    @Query("SELECT CM FROM ComboMeal CM WHERE CM.totalPrice BETWEEN :min AND :max AND CM.isActive=TRUE")
    List<ComboMeal> findByPriceBetween(@Param("min") Double min, @Param("max") Double max);

    @Query("SELECT CM FROM ComboMeal CM JOIN CM.menuItems M WHERE M.id= :menuItemId AND CM.isActive=TRUE AND M.isActive=TRUE")
    List<ComboMeal> findByMenuItemId(@Param("menuItemId") Integer menuItemId);
}
