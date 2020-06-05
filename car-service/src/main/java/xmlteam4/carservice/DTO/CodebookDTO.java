package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodebookDTO {

    private Long carBrandId;
    private Long carClassId;
    private Long carModelId;
    private Long fuelTypeId;
    private Long transmissionId;
    private CarBrandDTO carBrandDTO;

    public CodebookDTO() {
    }
}