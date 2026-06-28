package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.DTOs.Request.DeliveryDriverRequestDTO;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Integer> {
    DeliveryDriver registerDriver(DeliveryDriverRequestDTO request);
    List<DeliveryDriver> getAllDrivers();
    List<DeliveryDriver> getOnlineDrivers(boolean status);
    DeliveryDriver toggleOnlineStatus(Integer id, boolean isOnline);
    DeliveryDriver updateLocation(Integer id, Double lat, Double lng);
    List<DeliveryDriver> getDeliveryHistory(Integer id);
    DeliveryDriver getActiveDelivery(Integer id);
}
