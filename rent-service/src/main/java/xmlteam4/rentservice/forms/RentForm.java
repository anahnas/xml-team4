package xmlteam4.rentservice.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RentForm {
    private Long carId;
    /*private LocalDateTime startDate;
    private LocalDateTime endDate;*/
    private Date startDate;
    private Date endDate;
    private Long clientId; //dolazice kroz header zakucaj sad u formi clientId, dodacemo samo kasnije
    private Long advertiserId;
}
