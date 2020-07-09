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
    private CarModelDTO carModelDTO;
    private CarClassDTO carClassDTO;
    private FuelTypeDTO fuelTypeDTO;
    private TransmissionDTO transmissionDTO;
    private LocationDTO locationDTO;

    public CodebookDTO() {
    }

    @Override
    public String toString() {
        return "CodebookDTO{" +
                "carBrandId=" + carBrandId +
                ", carClassId=" + carClassId +
                ", carModelId=" + carModelId +
                ", fuelTypeId=" + fuelTypeId +
                ", transmissionId=" + transmissionId +
                ", carBrandDTO=" + carBrandDTO +
                ", carModelDTO=" + carModelDTO +
                ", carClassDTO=" + carClassDTO +
                ", fuelTypeDTO=" + fuelTypeDTO +
                ", transmissionDTO=" + transmissionDTO +
                ", locationDTO=" + locationDTO +
                '}';
    }
}
