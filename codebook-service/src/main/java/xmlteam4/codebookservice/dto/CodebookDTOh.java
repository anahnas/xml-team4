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
public class CodebookDTOh {
    private List<CarBrandDTOh> carBrandDTOhs;
    private List<CarClassDTOh> carClassDTOhs;
    private List<CarModelDTOh> carModelDTOhs;
    private List<FuelTypeDTOh> fuelTypeDTOhs;
    private List<TransmissionDTOh> transmissionDTOhs;

    public CodebookDTOh(List<CarBrand> carBrands, List<CarClass> carClasses, List<CarModel> carModels,
                        List<FuelType> fuelTypes, List<Transmission> transmissions) {
        this.carBrandDTOhs = new ArrayList<>();
        this.carClassDTOhs = new ArrayList<>();
        this.carModelDTOhs = new ArrayList<>();
        this.fuelTypeDTOhs = new ArrayList<>();
        this.transmissionDTOhs = new ArrayList<>();

        for (CarBrand carBrand : carBrands)
            this.carBrandDTOhs.add(new CarBrandDTOh(carBrand));
        for (CarClass carClass : carClasses)
            this.carClassDTOhs.add(new CarClassDTOh(carClass));
        for (CarModel carModel : carModels)
            this.carModelDTOhs.add(new CarModelDTOh(carModel));
        for (FuelType fuelType : fuelTypes)
            this.fuelTypeDTOhs.add(new FuelTypeDTOh(fuelType));
        for (Transmission transmission : transmissions)
            this.transmissionDTOhs.add(new TransmissionDTOh(transmission));
    }
}
