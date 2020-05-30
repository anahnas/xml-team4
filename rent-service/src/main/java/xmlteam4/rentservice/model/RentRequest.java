package xmlteam4.rentservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column
    private Long clientId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus;

    public RentRequest() {
    }

    public RentRequest(LocalDateTime startDate, LocalDateTime endDate, Long clientId, RentStatus rentStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientId = clientId;
        this.rentStatus = rentStatus;
    }
}
