package xmlteam4.carservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Advertisement", namespace = "http://localhost:8081/car-service-schema")
@XmlRootElement(name = "advertisementClass")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @XmlElement
    private Car car;

    @Column
    @XmlElement
    private Long advertiserId;

    @Column
    @XmlElement
    private Date startDate;

    @Column
    @XmlElement
    private Date endDate;

    public Advertisement() {
    }
}
