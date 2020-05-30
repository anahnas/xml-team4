package xmlteam4.codebookservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class CarModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand;
    @ManyToOne
    private CarClass carClass;

    public CarModel() {
    }

    public CarModel(String name, CarBrand carBrand, CarClass carClass) {
        this.name = name;
        this.carBrand = carBrand;
        this.carClass = carClass;
    }
}
