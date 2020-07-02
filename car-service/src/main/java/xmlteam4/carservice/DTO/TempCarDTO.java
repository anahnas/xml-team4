package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempCarDTO {
    private Long id;
    private String carModelId;
    private String carBrandId;
    private String fuelTypeId;
    private String locationId;
    private String transmissionId;
    private String carClassId;
    private Double pricePerDay;
    private Double pricePerKm;
    private boolean limitedKms;
    private Double limitKmsPerDay;
    private Double kmage;
    private boolean waiver;
    private int availableChildSeats;

    public TempCarDTO() {
    }
}
