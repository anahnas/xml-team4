package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.FuelType;

@Getter
@Setter
@NoArgsConstructor
public class FuelTypeDTO {
    private Long id;
    private String name;

    public FuelTypeDTO(FuelType fuelType) {
        this.id = fuelType.getId();
        this.name = fuelType.getName();
    }
}
