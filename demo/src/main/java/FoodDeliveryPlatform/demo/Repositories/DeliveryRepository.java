package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.Delivery;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<DeliveryDriver,Integer> {
    @Query("SELECT D FROM DeliveryDriver D WHERE D.deliveryDriver.id= :driverId AND D.status= :status AND D.deliveryDriver.isActive=TRUE")
    List<DeliveryDriver> findByDeliveryDriverIdAndStatus(@Param("driverId") Integer driverId, @Param("status") String status);
}
