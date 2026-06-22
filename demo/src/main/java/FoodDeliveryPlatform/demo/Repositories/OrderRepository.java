package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.Delivery;
import FoodDeliveryPlatform.demo.Entities.Orders;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT O FROM Orders O WHERE O.customer.id= :id AND O.isActive=TRUE AND O.customer.isActive=TRUE")
    List<Orders> findByCustomerId(@Param("id") Integer customerId);

    @Query("SELECT O FROM Orders O WHERE O.restaurant.id= :restaurantId AND O.status= :status AND O.isActive=TRUE AND O.restaurant.isActive=TRUE")
    List<Orders> findByRestaurantIdAndStatus(@Param("restaurantId") Integer restaurantId, @Param("status") String status);

    @Query("SELECT O FROM Orders O WHERE O.orderDate BETWEEN :start AND :end AND O.isActive=TRUE")
    List<Orders> findByOrderDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT COUNT(O) FROM Orders O WHERE O.restaurant.id= :restaurantId AND O.status= :status AND O.isActive=TRUE AND O.restaurant.isActive=TRUE")
    Integer countCompletedOrdersByRestaurantId(@Param("restaurantId") Integer restaurantId, @Param("status") String status);

    @Query("SELECT COALESCE(SUM(O.totalAmount),0.0) FROM Orders O WHERE O.orderDate= :date AND O.status= TRUE AND O.isActive=TRUE")
    Double sumTotalAmountDeliveredOrdersOnSpecifyDate(@Param("date") LocalDate date);

    /*@Query("SELECT D FROM Delivery D WHERE D.DeliveryDriver.id=:driverId AND D.status=: status AND D.DeliveryDriver.isActive=TRUE")
    List<Delivery> findByDeliveryDriverIdAndStatus(@Param("driverId") Integer driverId, @Param("status") String status);*/
}
