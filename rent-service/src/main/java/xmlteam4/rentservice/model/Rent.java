package xmlteam4.rentservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.rentservice.dto.CarDTOBasic;
import xmlteam4.rentservice.forms.RentForm;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long bundleId;

    @Column
    private Long carId;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private Date startDate2;

    @Column
    private Date endDate2;

    @Column
    private LocalDateTime created;

    /*@Column
    private Set<Long> carsForRent;*/


    @Column
    private Long clientId;

    @Column
    private Long advertiserId;

    @Column
    @Enumerated(EnumType.STRING)
    private RentStatus status;

    @Column
    private Boolean waiver;

    @Column
    private Double priceInitial;

    @Column
    private Double pricePenalty;


    public Rent(RentForm rentForm) {
        bundleId = null;
        this.carId = rentForm.getCarId();
        this.startDate = rentForm.getStartDate();
        this.endDate = rentForm.getEndDate();
        this.clientId = rentForm.getClientId();
        this.status = RentStatus.PENDING;
        this.advertiserId = rentForm.getAdvertiserId();
    }
    public Rent(RentForm rentForm, CarDTOBasic car) {
        bundleId = null;
        this.carId = rentForm.getCarId();
        this.startDate = rentForm.getStartDate();
        this.endDate = rentForm.getEndDate();
        this.clientId = rentForm.getClientId();
        this.status = RentStatus.PENDING;
        this.advertiserId = rentForm.getAdvertiserId();
        this.waiver = rentForm.getWaiver();
        this.priceInitial = DAYS.between(rentForm.getStartDate(), rentForm.getEndDate()) * car.getPricePerDay();
        if (this.waiver)
            this.priceInitial += DAYS.between(rentForm.getStartDate(), rentForm.getEndDate()) * car.getWaiverPricePerDay();
        this.pricePenalty = 0D;
    }

    public Rent(RentForm rentForm, Long bundleId) {
        this.bundleId = bundleId;
        this.carId = rentForm.getCarId();
        this.startDate = rentForm.getStartDate();
        this.endDate = rentForm.getEndDate();
        this.clientId = rentForm.getClientId();
        this.status = RentStatus.PENDING;
        this.advertiserId = rentForm.getAdvertiserId();

    }


}
