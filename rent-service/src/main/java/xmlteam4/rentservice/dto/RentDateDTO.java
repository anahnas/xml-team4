package xmlteam4.rentservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class RentDateDTO {
    java.time.LocalDateTime startDate;
    LocalDateTime endDate;
    ArrayList<Long> carIds;

    public RentDateDTO() {
    }
}
