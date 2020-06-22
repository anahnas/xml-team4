package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.CarBrand;

@Getter
@Setter
@NoArgsConstructor
public class CarBrandDTO {
    private Long id;
    private String name;

    public CarBrandDTO(CarBrand carBrand) {
        this.id = carBrand.getId();
        this.name = carBrand.getName();
    }
}
