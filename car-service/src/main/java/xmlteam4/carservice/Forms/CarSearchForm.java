package xmlteam4.carservice.Forms;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CarSearchForm {
    private Date startDate;
    private Date endDate;
    private Long locationId;

    public CarSearchForm() { }
}
