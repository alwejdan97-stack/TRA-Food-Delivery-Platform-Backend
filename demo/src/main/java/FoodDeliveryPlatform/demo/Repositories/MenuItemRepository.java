package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {
    @Query("SELECT M FROM MenuItem M WHERE M.Restaurant.id=: id AND M.isActive=TRUE AND M.Restaurant.isActive=TRUE")
    List<MenuItem>  findByRestaurantId(@Param("id") Integer id);

    @Query("SELECT M FROM MenuItem M WHERE M.Restaurant.id=: id AND M.isActive=TRUE")
    List<MenuItem> findByRestaurantIdAndIsAvailableTrue(Integer id);

    @Query("SELECT M FROM MenuItem M WHERE M.isVegetarian= TRUE AND M.isActive=TRUE")
    List<MenuItem> findByIsVegetarianTrue();

    @Query("SELECT M FROM MenuItem M WHERE M.price BETWEEN :min AND :max AND M.isActive=TRUE")
    List<MenuItem> findByPriceBetween(Double min, Double max);

    @Query("SELECT CM FROM ComboMeal CM JOIN CM.MenuItem M WHERE M.id=:MenuItemId AND CM.isActive=TRUE AND M.isActive=TRUE AND CM.Restaurant.isActive=TRUE")
    List<MenuItem> findByMenuItemId(@Param("MenuItemId") Integer menuItemId);
}
