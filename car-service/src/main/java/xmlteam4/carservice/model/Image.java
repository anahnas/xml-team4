package xmlteam4.carservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path")
    private String path;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;

    public Image() {
    }

    public Image(Long id, String path, Car car) {
        this.id = id;
        this.path = path;
        this.car = car;
    }
}
