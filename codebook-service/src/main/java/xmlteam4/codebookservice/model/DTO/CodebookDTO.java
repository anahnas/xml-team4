package xmlteam4.codebookservice.model.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.codebookservice.model.*;

@Getter
@Setter
public class CodebookDTO {

    public CarBrandDTO carBrandDTO;
    public Long carBrandId;
    public CarModelDTO carModelDTO;
    public Long carModelId;
    public CarClassDTO carClassDTO;
    public Long carClassId;
    public TransmissionDTO transmissionDTO;
    public Long transmissionId;
    public FuelTypeDTO fuelTypeDTO;
    public Long fuelTypeId;


    public CodebookDTO() {}
}
