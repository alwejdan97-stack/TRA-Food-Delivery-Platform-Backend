package FoodDeliveryPlatform.demo.Services;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    createCustomer(CustomerRequestDTO dto)
createCustomer(CustomerRequestDTO dto, CustomerAddressRequestDTO
            initialAddress)
addAddress(Integer customerId, CustomerAddressRequestDTO address)
updateLoyaltyPoints(Integer customerId, int points)
applyLoyaltyPenalty(Integer customerId, int pointsDeducted)
deactivateCustomer(Integer customerId)
}
