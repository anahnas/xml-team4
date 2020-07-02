package xmlteam4.userservice.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import java.time.LocalDateTime;

@Getter
@Setter
public class Rent {
    private Long id;
    private Long carId;
    private Long clientId;
    private RentStatus status;

    public Rent() {
    }
}
