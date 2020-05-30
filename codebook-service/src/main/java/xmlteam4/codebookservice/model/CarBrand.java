package xmlteam4.codebookservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class CarBrand {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @JsonIgnore
    @OneToMany
    private List<CarModel> carModels;

    public CarBrand() {
    }

    public CarBrand(String name) {
        this.name = name;
    }
}
