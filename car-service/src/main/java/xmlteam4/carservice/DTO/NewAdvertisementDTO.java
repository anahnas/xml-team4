package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.carservice.model.Image;

import java.util.ArrayList;
import java.util.Date;

@Setter
@Getter
public class NewAdvertisementDTO {

    private Long carBrandId;
    private Long carClassId;
    private Long carModelId;
    private Long fuelTypeId;
    private Long transmissionId;

    private String imagePath;

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

    private String roleType;

    public NewAdvertisementDTO() {
    }}
