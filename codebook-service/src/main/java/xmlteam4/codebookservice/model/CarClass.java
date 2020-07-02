package xmlteam4.codebookservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class CarClass {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    public CarClass() {
    }

    public CarClass(String name) {
        this.name = name;
    }
}
