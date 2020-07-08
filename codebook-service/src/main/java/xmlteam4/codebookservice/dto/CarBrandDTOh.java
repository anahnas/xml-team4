package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.CarBrand;

@Getter
@Setter
@NoArgsConstructor
public class CarBrandDTOh {
    private Long id;
    private String name;

    public CarBrandDTOh(CarBrand carBrand) {
        this.id = carBrand.getId();
        this.name = carBrand.getName();
    }
}
