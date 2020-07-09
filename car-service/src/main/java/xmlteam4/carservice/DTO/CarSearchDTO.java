package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CarSearchDTO {
    private Date startDate;
    private Date endDate;
    private Long locationId;
    
    private Long carModelId;
    private Long carClassId;
    public Long transmissionId;
    public Long fuelTypeId;

    private boolean limitedKms;
    private Double kmage;
    private boolean waiver;
    private int availableChildSeats;

    public CarSearchDTO() { }

    @Override
    public String toString() {
        return "CarSearchDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", locationId=" + locationId +
                ", carModelId=" + carModelId +
                ", carClassId=" + carClassId +
                ", transmissionId=" + transmissionId +
                ", fuelTypeId=" + fuelTypeId +
                ", limitedKms=" + limitedKms +
                ", kmage=" + kmage +
                ", waiver=" + waiver +
                ", availableChildSeats=" + availableChildSeats +
                '}';
    }
}
