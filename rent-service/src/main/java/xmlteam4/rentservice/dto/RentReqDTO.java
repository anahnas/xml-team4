package xmlteam4.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.rentservice.model.RentStatus;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentReqDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private RentStatus status;
    private Long carId;
    private Long cliendId;
    private Long bundleId;
    private LocalDateTime created;
    private Long advertiser_id;
    // private Long carsForRent;

}
