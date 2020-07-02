package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CodebookDTO {
    private List<CarBrandDTO> carBrandDTOs;
    private List<CarClassDTO> carClassDTOs;
    private List<CarModelDTO> carModelDTOs;
    private List<FuelTypeDTO> fuelTypeDTOs;
    private List<TransmissionDTO> transmissionDTOs;

    public CodebookDTO(List<CarBrand> carBrands, List<CarClass> carClasses, List<CarModel> carModels,
                        List<FuelType> fuelTypes, List<Transmission> transmissions) {
        this.carBrandDTOs = new ArrayList<>();
        this.carClassDTOs = new ArrayList<>();
        this.carModelDTOs = new ArrayList<>();
        this.fuelTypeDTOs = new ArrayList<>();
        this.transmissionDTOs= new ArrayList<>();

        for (CarBrand carBrand : carBrands)
            this.carBrandDTOs.add(new CarBrandDTO(carBrand));
        for (CarClass carClass : carClasses)
            this.carClassDTOs.add(new CarClassDTO(carClass));
        for (CarModel carModel : carModels)
            this.carModelDTOs.add(new CarModelDTO(carModel));
        for (FuelType fuelType : fuelTypes)
            this.fuelTypeDTOs.add(new FuelTypeDTO(fuelType));
        for (Transmission transmission : transmissions)
            this.transmissionDTOs.add(new TransmissionDTO(transmission));
    }
}
