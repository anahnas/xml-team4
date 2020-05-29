package xmlteam4.codebookservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class FuelType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;

    public FuelType() {
    }

    public FuelType(String type) {
        this.type = type;
    }


}
