package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.DeliveryDriverRequestDTO;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Repositories.DeliveryDriverRepository;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import FoodDeliveryPlatform.demo.Utilities.HelperUtils;
import org.springframework.stereotype.Service;

@Service
public class DeliveryDriverService {
    DeliveryDriverRepository driverRepository;
    DeliveryRepository deliveryRepository;

    public DeliveryDriver registerDriver(DeliveryDriverRequestDTO request) {
        DeliveryDriver newDriver = new DeliveryDriver();
        newDriver.setFirstName(request.getFirstName());
        newDriver.setLastName(request.getLastName());
        newDriver.setDriverCode(HelperUtils.generateCode("DD-01"));
        newDriver.setIsOnline(false);

        return driverRepository.save(newDriver);
    }
}
