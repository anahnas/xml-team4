package xmlteam4.carservice.DTO.codebookh;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CodebookDTOh {
    private List<CarBrandDTOh> carBrandDTOhs;
    private List<CarClassDTOh> carClassDTOhs;
    private List<CarModelDTOh> carModelDTOhs;
    private List<FuelTypeDTOh> fuelTypeDTOhs;
    private List<TransmissionDTOh> transmissionDTOhs;
}
