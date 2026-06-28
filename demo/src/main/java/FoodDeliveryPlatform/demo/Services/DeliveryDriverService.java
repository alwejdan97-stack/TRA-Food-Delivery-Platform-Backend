package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Repositories.DeliveryDriverRepository;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryDriverService {
    DeliveryDriverRepository driverRepository;
    DeliveryRepository deliveryRepository;

    public DeliveryDriver registerDriver(Delivery request) {
        Driver newDriver = new Driver();
        newDriver.setName(request.getName());
        newDriver.setIsOnline(false); // New drivers start offline

        return driverRepository.save(newDriver);
    }
}
