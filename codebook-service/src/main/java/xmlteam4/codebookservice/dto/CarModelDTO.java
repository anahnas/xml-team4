package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.CarModel;

@Getter
@Setter
@NoArgsConstructor
public class CarModelDTO {
    private Long id;
    private String name;
    private Long brandId;
    private Long classId;

    public CarModelDTO(CarModel carModel) {
        this.id = carModel.getId();
        this.name = carModel.getName();
        this.brandId = carModel.getCarBrand().getId();
        this.classId = carModel.getCarClass().getId();
    }
}
