package xmlteam4.codebookservice.model.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.codebookservice.model.*;

@Getter
@Setter
public class CodebookDTO {

    public CarBrandDTO carBrandDTO;
    public Long carBrandId;
    //public CarModel carModel;
    public Long carModelId;
    //public CarClass carClass;
    public Long carClassId;
    //public Transmission transmission;
    public Long transmissionId;
    //public FuelType fuelType;
    public Long fuelTypeId;


    public CodebookDTO() {}
}
