package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Request.CustomerAddressRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.CustomerRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CustomerAddressResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CustomerPatchDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CustomerResponseDTO;
import FoodDeliveryPlatform.demo.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/createCustomer/createCustomer")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO dto) {
        List<CustomerResponseDTO> responseList = customerService.createCustomer(dto);
        return new ResponseEntity<>(responseList.get(0), HttpStatus.CREATED);
    }

    @PostMapping("/addAddress/addAddress/{id}/addresses ")
    public ResponseEntity<List<CustomerAddressResponseDTO>> addAddress(@PathVariable Integer id, @Valid @RequestBody CustomerAddressRequestDTO addressDto) {
        List<CustomerAddressResponseDTO> response = customerService.addAddress(id, addressDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Integer id) {
        CustomerResponseDTO response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCustomerByEmail/email/{email}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByEmail(@PathVariable String email) {
        CustomerResponseDTO response = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCustomerOrders/{id}/orders")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomerOrders(@PathVariable Integer id) {
        List<CustomerResponseDTO> orders = customerService.getCustomerOrders(id);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> response = customerService.getAllCustomers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCustomerAddresses/{id}/addresses")
    public ResponseEntity<List<CustomerAddressResponseDTO>> getCustomerAddresses(@PathVariable Integer id) {
        List<CustomerAddressResponseDTO> response = customerService.getCustomerAddresses(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/setDefaultAddress/addresses/{addressId}/default")
    public ResponseEntity<CustomerAddressResponseDTO> setDefaultAddress(@PathVariable Integer addressId) {
        CustomerAddressResponseDTO response = customerService.setDefaultAddress(addressId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/deductLoyaltyPoints/{id}/loyalty/deduct/{points}")
    public ResponseEntity<CustomerResponseDTO> deductLoyaltyPoints(
            @PathVariable Integer id,
            @PathVariable int points) {
        CustomerResponseDTO response = customerService.applyLoyaltyPenalty(id, points);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/addLoyaltyPoints/{id}/loyalty/add/{points}")
    public ResponseEntity<CustomerResponseDTO> addLoyaltyPoints(
            @PathVariable Integer id,
            @PathVariable int points) {
        CustomerResponseDTO response = customerService.updateLoyaltyPoints(id, points);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/deactivateCustomer/{id}/deactivate")
    public ResponseEntity<CustomerResponseDTO> deactivateCustomer(@PathVariable Integer id) {
        CustomerResponseDTO response = customerService.deactivateCustomer(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteAddress/addresses/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer addressId) {
        customerService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}
