package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("SELECT C FROM Customer C WHERE C.email=:email AND C.isActive=TRUE")
    List<Customer> findByEmail(@Param("email") String email);

    @Query("SELECT C FROM Customer C WHERE C.loyaltyPoints>=:loyaltyPoints AND C.isActive=TRUE")
    List<Customer> findByLoyaltyPointsGreaterThanEqual(@Param("loyaltyPoints") Integer points);

    @Query("SELECT C FROM Customer C WHERE C.city=:city AND C.createdDate BETWEEN :startDate AND :endDate")
    List<Customer> findByRegistrationDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
