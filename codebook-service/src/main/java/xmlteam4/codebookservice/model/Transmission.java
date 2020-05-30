package xmlteam4.codebookservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Transmission {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;

    public Transmission() {
    }

    public Transmission(String type) {
        this.type = type;
    }
}
