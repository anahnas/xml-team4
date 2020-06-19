package xmlteam4.rentservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.rentservice.forms.RentForm;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    public Rent(RentForm rentForm) {
        bundleId = null;
        this.carId = rentForm.getCarId();
        this.startDate = rentForm.getStartDate();
        this.endDate = rentForm.getEndDate();
        this.clientId = rentForm.getClientId();
        this.status = RentStatus.PENDING;
    }

    public Rent(RentForm rentForm, Long bundleId) {
        this.bundleId = bundleId;
        this.carId = rentForm.getCarId();
        this.startDate = rentForm.getStartDate();
        this.endDate = rentForm.getEndDate();
        this.clientId = rentForm.getClientId();
        this.status = RentStatus.PENDING;
    }
}
