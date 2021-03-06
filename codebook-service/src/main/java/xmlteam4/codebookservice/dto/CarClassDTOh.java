package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.CarClass;

@Getter
@Setter
@NoArgsConstructor
public class CarClassDTOh {
    private Long id;
    private String name;

    public CarClassDTOh(CarClass carClass) {
        this.id = carClass.getId();
        this.name = carClass.getName();
    }
}
