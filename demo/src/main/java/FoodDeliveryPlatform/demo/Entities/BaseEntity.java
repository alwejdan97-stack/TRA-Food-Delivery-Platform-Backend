package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;*/

    private Boolean isAvailable;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private Boolean isActive;
}
