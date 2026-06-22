package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Integer> {
    @Query("SELECT CA FROM Customer CA WHERE CA.city=:city AND CA.isActive=TRUE")
    List<CustomerAddress> findByCity(@Param("city") String city);
}
