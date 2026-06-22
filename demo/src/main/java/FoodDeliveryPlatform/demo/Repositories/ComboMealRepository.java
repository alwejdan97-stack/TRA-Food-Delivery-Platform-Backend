package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.ComboMeal;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import FoodDeliveryPlatform.demo.Entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComboMealRepository extends JpaRepository<ComboMeal,Integer> {
    @Query("SELECT CM FROM ComboMeal CM WHERE CM.Restaurant.id=: id AND CM.isActive=TRUE AND CM.Restaurant.isActive=TRUE")
    List<MenuItem> findByRestaurantId(@Param("id") Integer id);

    @Query("SELECT CM FROM ComboMeal CM WHERE CM.Restaurant.id=: id AND CM.isAvailable=TRUE AND CM.isActive=TRUE AND CM.Restaurant.isActive=TRUE")
    List<MenuItem> findByRestaurantIdAndIsAvailableTrue(Integer id);

    @Query("SELECT CM FROM ComboMeal CM WHERE CM.isVegetarian= TRUE AND CM.isActive=TRUE")
    List<MenuItem> findByIsVegetarianTrue();

    @Query("SELECT CM FROM ComboMeal CM WHERE CM.totalPrice BETWEEN :min AND :max AND CM.isActive=TRUE")
    List<MenuItem> findByPriceBetween(@Param("min") Double min, @Param("max") Double max);

    @Query("SELECT CM FROM ComboMeal CM JOIN CM.MenuItem M WHERE M.id=:MenuItemId AND CM.isActive=TRUE AND M.isActive=TRUE AND CM.Restaurant.isActive=TRUE")
    List<MenuItem> findByMenuItemId(@Param("MenuItemId") Integer menuItemId);
}
