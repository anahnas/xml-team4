package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.carservice.model.Advertisement;
import xmlteam4.carservice.model.Image;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class AdvertisementDTO {
    private Long id;
    private CarDTO carDTO;
    private Date startDate;
    private Date endDate;

    public AdvertisementDTO() {
    }
    public  AdvertisementDTO(Advertisement advertisementDTO) {
        this.id = advertisementDTO.getId();
        this.startDate = advertisementDTO.getStartDate();
        this.endDate = advertisementDTO.getEndDate();

    }


}
