package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }


}
