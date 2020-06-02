package xmlteam4.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class CarCalendar {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long carId;
    @ElementCollection(targetClass=Long.class)
    private List<Long> rentalIds;

    public CarCalendar() {
    }

    public void setRentalIds(List<Long> rentalIds) {
        this.rentalIds = rentalIds;
    }
}
