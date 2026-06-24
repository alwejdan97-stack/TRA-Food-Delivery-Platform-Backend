package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.CustomerAddressRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.CustomerRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CustomerAddressResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CustomerResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.CustomerAddressRepository;
import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerAddressRepository customerAddressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository) {
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    public List<CustomerResponseDTO> createCustomer(CustomerRequestDTO dto){

        Customer customer=new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setPasswordHash(dto.getPassword());

        Customer newCustomer=customerRepository.save(customer);

        List<CustomerResponseDTO> customerResponseDTOList=new ArrayList<>();

        customerResponseDTOList.add(CustomerResponseDTO.convertToDTO(newCustomer));

        //convert customer to CustomerResponseDTO
        /*customerResponseDTO.setId(newCustomer.getId());
        customerResponseDTO.setFirstName(newCustomer.getFirstName());
        customerResponseDTO.setLastName(newCustomer.getLastName());
        customerResponseDTO.setPhone(newCustomer.getPhone());
        customerResponseDTO.setEmail(newCustomer.getEmail());
        customerResponseDTO.setPassword(newCustomer.getPasswordHash());*/

        return customerResponseDTOList;
    }

    public List<CustomerResponseDTO> createCustomer(CustomerRequestDTO dto, CustomerAddressRequestDTO initialAddress){
        Customer customer=new Customer();

        //adding customer
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setPasswordHash(dto.getPassword());

        CustomerAddress customerAddress=new CustomerAddress();
        //adding address
        customerAddress.setId(initialAddress.getId());
        customerAddress.setStreet(initialAddress.getStreet());
        customerAddress.setCity(initialAddress.getCity());
        customerAddress.setBuilding(initialAddress.getBuilding());
        customerAddress.setCustomer(customer);

        //assign address to customer object
        customer.getCustomerAddresses().add(customerAddress);

        Customer newCustomer=customerRepository.save(customer);

        List<CustomerResponseDTO> customerResponseDTOList=new ArrayList<>();
        customerResponseDTOList.add(CustomerResponseDTO.convertToDTO(newCustomer));

        return customerResponseDTOList;
    }

    public List<CustomerAddressResponseDTO> addAddress(Integer customerId, CustomerAddressRequestDTO address){
        Optional<Customer> customers=customerRepository.findById(customerId);
        if(address==null || !customerRepository.existsById(customerId)){
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        Customer customer=customers.get();
        CustomerAddress customerAddress=new CustomerAddress();

        customerAddress.setId(address.getId());
        customerAddress.setStreet(address.getStreet());
        customerAddress.setCity(address.getCity());
        customerAddress.setBuilding(address.getBuilding());
        customerAddress.setCustomer(customer);

        customer.getCustomerAddresses().add(customerAddress);
        customer.setUpdatedDate(LocalDate.now());

        Customer updatedCustomer=customerRepository.save(customer);

        List<CustomerAddressResponseDTO> dtoList=new ArrayList<>();

        for(CustomerAddress cs:updatedCustomer.getCustomerAddresses()){
            CustomerAddressResponseDTO customerAddressResponseDTO=new CustomerAddressResponseDTO();
            customerAddressResponseDTO.setStreet(cs.getStreet());
            customerAddressResponseDTO.setBuilding(cs.getBuilding());
            customerAddressResponseDTO.setCity(cs.getCity());

            dtoList.add(customerAddressResponseDTO);
        }

        return dtoList;
    }

    public CustomerResponseDTO updateLoyaltyPoints(Integer customerId, int points){
        Optional<Customer> customers=customerRepository.findById(customerId);

        if (customers.isEmpty() || !customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        Customer customer=customers.get();

        customer.setLoyaltyPoints(customer.getLoyaltyPoints()+ points);
        customer.setUpdatedDate(LocalDate.now());

        Customer updatedCustomer=customerRepository.save(customer);

        return CustomerResponseDTO.convertToDTO(updatedCustomer);
    }

    public CustomerResponseDTO applyLoyaltyPenalty(Integer customerId, int pointsDeducted){
        Optional<Customer> customers=customerRepository.findById(customerId);
        if (customers.isEmpty() || !customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        Customer customer=customers.get();
        int existPoint=customer.getLoyaltyPoints();
        customer.setLoyaltyPoints(Math.max(0,existPoint-pointsDeducted));

        customer.setUpdatedDate(LocalDate.now());

        Customer updatedCustomer=customerRepository.save(customer);

        return CustomerResponseDTO.convertToDTO(updatedCustomer);
    }

    public CustomerResponseDTO deactivateCustomer(Integer customerId){
        Optional<Customer> customers=customerRepository.findById(customerId);
        if (customers.isEmpty() || !customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        Customer customer=customers.get();

        customer.setIsActive(false);

        customer.setUpdatedDate(LocalDate.now());

        Customer updatedCustomer=customerRepository.save(customer);

        return CustomerResponseDTO.convertToDTO(updatedCustomer);
    }
}
