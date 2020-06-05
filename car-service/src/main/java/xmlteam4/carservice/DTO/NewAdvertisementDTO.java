package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class NewAdvertisementDTO {

    private Long carBrandId;
    private Long carClassId;
    private Long carModelId;
    private Long fuelTypeId;
    private Long transmissionId;

    private boolean limitedKms;
    private Double limitKmsPerDay;

    private Long advertiserId;

    private Date startDate;

    private Date endDate;

    private Double pricePerDay;

    private Double pricePerKm;

    private boolean Waiver;

    private int availableChildSeats;

    private Double kmage;

    public NewAdvertisementDTO() {
    }}
