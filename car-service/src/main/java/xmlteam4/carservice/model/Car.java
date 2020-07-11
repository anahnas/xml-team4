package xmlteam4.carservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Set;


@Entity
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Car", namespace = "http://localhost:8081/car-service")
@XmlRootElement(name = "carClass")
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @XmlElement
    private Long id;
    @Column
    @XmlElement
    private Long carModelId;
    @Column
    @XmlElement
    private Long carBrandId;
    @Column
    @XmlElement
    private Long fuelTypeId;
    @Column
    @XmlElement
    private Long locationId;
    @Column
    @XmlElement
    private Long transmissionId;
    @Column
    @XmlElement
    private Long carClassId;
    @Column
    @XmlElement
    private Double pricePerDay;
    @Column
    @XmlElement
    private Double pricePerKm;
    @Column
    @XmlElement
    private boolean limitedKms;
    @Column
    @XmlElement
    private Double limitKmsPerDay;
    @Column
    @XmlElement
    private Double kmage;
    @Column
    @XmlElement
    private boolean waiver;
    @Column
    private Double waiverPricePerDay;
    @Column
    @XmlElement
    private int availableChildSeats;
    @Column
    //@XmlElement
    @ElementCollection(targetClass=Long.class)
    private Set<Long> carRatingIds;
    @Column
    @XmlElement
    private Long ownerId;
    @Column
    @XmlElement
    private String imagePath;

    @Column

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
        this.waiver = waiver;
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
