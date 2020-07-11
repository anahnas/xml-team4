package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class RentDateDTO {
    LocalDateTime startDate;
    LocalDateTime endDate;
    ArrayList<Long> carIds = new ArrayList<>();
    public RentDateDTO() {
    }
}
