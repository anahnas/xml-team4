package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.FuelType;

@Getter
@Setter
@NoArgsConstructor
public class FuelTypeDTOh {
    private Long id;
    private String name;

    public FuelTypeDTOh(FuelType fuelType) {
        this.id = fuelType.getId();
        this.name = fuelType.getName();
    }
}
