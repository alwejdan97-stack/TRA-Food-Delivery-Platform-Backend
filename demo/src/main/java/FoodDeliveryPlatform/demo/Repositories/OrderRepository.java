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
    @Query("SELECT O FROM Orders O WHERE O.Customer.id=: id AND O.isActive=TRUE AND O.Customer.isActive=TRUE")
    List<Orders> findByCustomerId(@Param("id") Integer customerId);

    @Query("SELECT O FROM Orders O WHERE O.Restaurant.id=: restaurantId AND O.status=:status AND O.isActive=TRUE AND O.Resturant.isActive=TRUE")
    List<Orders> findByRestaurantIdAndStatus(@Param("restaurantId") Integer restaurantId, @Param("status") String status);

    @Query("SELECT O FROM Orders O WHERE O.orderDate BETWEEN :start AND :end AND O.isActive=TRUE")
    List<Orders> findByOrderDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT COUNT(O) FROM Orders O WHERE O.Restaurant.id=: restaurantId AND O.status=:status AND O.isActive=TRUE AND O.Resturant.isActive=TRUE")
    List<Orders> countCompletedOrdersByRestaurantId(@Param("restaurantId") Integer restaurantId, @Param("status") String status);

    @Query("SELECT COALESCE(SUM(O),0.0) FROM Orders O WHERE CAST(O.orderDate AS LocalDate)=:date AND O.status=:DELIVERED AND O.isActive=TRUE")
    List<Orders> sumTotalAmountDeliveredOrdersOnSpecifyDate(@Param("date") LocalDate date);

    @Query("SELECT D FROM Delivery D WHERE D.DeliveryDriver.id=:id AND D.status=: status AND D.DeliveryDriver.isActive=TRUE")
    List<Delivery> findByDeliveryDriverIdAndStatus(Integer driverId, String status);
}
