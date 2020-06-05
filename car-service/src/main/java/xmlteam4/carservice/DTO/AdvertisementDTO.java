package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AdvertisementDTO {
    private Long id;
    private CarDTO carDTO;
    private Date startDate;
    private Date endDate;

    public AdvertisementDTO() {
    }
}
