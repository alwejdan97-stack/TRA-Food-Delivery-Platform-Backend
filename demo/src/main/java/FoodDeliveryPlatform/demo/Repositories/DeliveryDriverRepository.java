package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Integer> {
    @Query("SELECT DD FROM DeliveryDriver DD WHERE DD.isOnline= :status")
    List<DeliveryDriver> getOnlineDrivers(@Param("status") boolean isOnline);
    List<DeliveryDriver> findByIdAndIsOnline(Integer id, Boolean isOnline);

}
