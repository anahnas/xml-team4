package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.carservice.model.Image;

import java.util.Set;

@Setter
@Getter
public class CarDTO {
    private CarBrandDTO carBrandDTO;
    private CarModelDTO carModelDTO;
    private CarClassDTO carClassDTO;
    public TransmissionDTO transmissionDTO;
    public FuelTypeDTO fuelTypeDTO;
    private Long id;
    private Long carModelId;
    private Long carBrandId;
    private Long fuelTypeId;
    private Long locationId;
    private Long transmissionId;
    private Long carClassId;
    private Double pricePerDay;
    private Double pricePerKm;
    private boolean limitedKms;
    private Double limitKmsPerDay;
    private Double kmage;
    private boolean Waiver;
    private int availableChildSeats;
    private Set<Long> carRatingIds;
    private Long ownerId;
    private Long imageId;
    // private List<Image> images;
    private Set<Long> promotionIds;

    public CarDTO() {
    }
}
