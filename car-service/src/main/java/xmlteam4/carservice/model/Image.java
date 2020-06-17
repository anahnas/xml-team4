package xmlteam4.carservice.model;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="slikica")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_path", length = 5000)
   //@Length(min = 0, max = 5000)
    private String path;

    public Image() {
    }

}
