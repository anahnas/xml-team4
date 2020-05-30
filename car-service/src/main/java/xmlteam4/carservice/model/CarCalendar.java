package xmlteam4.carservice.model;

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
    private List<Rental> rentals;

    public CarCalendar() {
    }
}
