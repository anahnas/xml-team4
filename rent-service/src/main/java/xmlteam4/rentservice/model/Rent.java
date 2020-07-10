package xmlteam4.rentservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.rentservice.dto.CarDTOBasic;
import xmlteam4.rentservice.forms.RentForm;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Long clientId;

    @Column
    @Enumerated(EnumType.STRING)
    private RentStatus status;

    @Column
    private Boolean waiver;

    @Column
    private Double priceInitial;

    @Column
    private Double pricePenalty;


    public Rent(RentForm rentForm, CarDTOBasic car) {
        bundleId = null;
        this.carId = rentForm.getCarId();
        this.startDate = rentForm.getStartDate();
        this.endDate = rentForm.getEndDate();
        this.clientId = rentForm.getClientId();
        this.status = RentStatus.PENDING;
        this.waiver = rentForm.getWaiver();
        this.priceInitial = DAYS.between(rentForm.getStartDate(), rentForm.getEndDate()) * car.getPricePerDay();
        if (this.waiver)
            this.priceInitial += DAYS.between(rentForm.getStartDate(), rentForm.getEndDate()) * car.getWaiverPricePerDay();
        this.pricePenalty = 0D;
    }
}
