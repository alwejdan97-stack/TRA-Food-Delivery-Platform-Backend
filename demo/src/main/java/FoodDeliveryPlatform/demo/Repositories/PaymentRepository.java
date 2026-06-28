package FoodDeliveryPlatform.demo.Repositories;

import FoodDeliveryPlatform.demo.Entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("SELECT P FROM Payment P WHERE (:method IS NULL OR P.paymentMethod = :method) AND (:status IS NULL OR P.status = :status) AND (:from IS NULL OR P.createdDate >= :from) AND (:to IS NULL OR P.createdDate <= :to)")
    Page<Payment> findFilteredPayments(@Param("method") String method, @Param("status") String status, @Param("from") LocalDate from, @Param("to") LocalDate to, Pageable pageable);
}
