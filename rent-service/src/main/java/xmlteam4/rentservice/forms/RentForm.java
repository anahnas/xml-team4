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
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Date startDate2;
    private Date endDate2;
    private Boolean waiver;
    private Long clientId; //dolazice kroz header zakucaj sad u formi clientId, dodacemo samo kasnije
    private Long advertiserId;

    @Override
    public String toString() {
        return "RentForm{" +
                "carId=" + carId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startDate2=" + startDate2 +
                ", endDate2=" + endDate2 +
                ", waiver=" + waiver +
                ", clientId=" + clientId +
                ", advertiserId=" + advertiserId +
                '}';
    }
}
