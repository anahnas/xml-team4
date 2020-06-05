package xmlteam4.carservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @ElementCollection(targetClass=Long.class)
    private Set<Long> carRatingIds;
    private Long ownerId;
    private String image;
    // private List<Image> images;
    @ElementCollection(targetClass=Long.class)
    private Set<Long> promotionIds;

    public Car(Long carBrandId, Long carModelId, Long fuelTypeId, Long transmissionId, Long carClassId, Double pricePerDay, Double pricePerKm, boolean limitedKms, Double limitKmsPerDay, Double kmage, boolean waiver, Integer availableChildSeats, Set<Long> carRatingIds, Long agentId, Set<Long> promotionIds) {
        this.carModelId = carModelId;
        this.carBrandId = carBrandId;
        this.fuelTypeId = fuelTypeId;
        this.transmissionId = transmissionId;
        this.carClassId = carClassId;
        this.pricePerDay = pricePerDay;
        this.pricePerKm = pricePerKm;
        this.limitedKms = limitedKms;
        this.limitKmsPerDay = limitKmsPerDay;
        this.kmage = kmage;
        Waiver = waiver;
        this.availableChildSeats = availableChildSeats;
        this.carRatingIds = carRatingIds;
        this.ownerId = agentId;
        this.promotionIds = promotionIds;
    }
    /*
    public Car(CarDTO carDTO) {
        this.carModelId = carDTO.getCarModel().getId();
        this.fuelTypeId = carDTO.getFuelType().getId();
        this.transmissionId = carDTO.getTransmission().getId();
        this.carClassId = carDTO.getCarClass().getId();
        this.pricePerDay = carDTO.getPricePerDay();
        this.pricePerKm = carDTO.getPricePerKm();
        this.limitedKms = carDTO.isLimitedKms();
        this.limitKmsPerDay = carDTO.getLimitKmsPerDay();
        this.kmage = carDTO.getKmage();
        Waiver = carDTO.isWaiver();
        this.availableChildSeats = carDTO.getAvailableChildSeats();
        this.carRatingIds = new HashSet<>();
        this.ownerId = carDTO.getAgentId();
        this.promotionIds = new HashSet<>();
    }*/

    public Car() { }
}
