package xmlteam4.rentservice.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RentForm {
    private Long carId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long clientId;
}
