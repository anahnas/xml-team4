package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.carservice.DTO.codebookh.*;
import xmlteam4.carservice.model.Car;

@Getter
@Setter
@NoArgsConstructor
public class CarDTOPretty {
    private Long id;
    private String carBrand;
    private String carModel;
    private String carClass;
    private String transmission;
    private String fuelType;
    private Double kmage;
    private Double pricePerDay;
    private Double pricePerKm;
    private boolean waiver;
    private int childSeats;

    public CarDTOPretty(Car car, CodebookDTOh codebookDTOh) {
        this.id = car.getId();
        for (CarBrandDTOh carBrand : codebookDTOh.getCarBrandDTOhs())
            if (carBrand.getId().equals(car.getCarBrandId())) {
                this.carBrand = carBrand.getName();
                break;
            }
        for (CarModelDTOh carModel : codebookDTOh.getCarModelDTOhs())
            if (carModel.getId().equals(car.getCarModelId())) {
                this.carModel = carModel.getName();
                break;
            }
        for (CarClassDTOh carClass : codebookDTOh.getCarClassDTOhs())
            if (carClass.getId().equals(car.getCarClassId())) {
                this.carClass = carClass.getName();
                break;
            }
        for (TransmissionDTOh transmission : codebookDTOh.getTransmissionDTOhs())
            if (transmission.getId().equals(car.getTransmissionId())) {
                this.transmission = transmission.getName();
                break;
            }
        for (FuelTypeDTOh fuelType : codebookDTOh.getFuelTypeDTOhs())
            if (fuelType.getId().equals(car.getFuelTypeId())) {
                this.fuelType = fuelType.getName();
                break;
            }
        this.kmage = car.getKmage();
        this.pricePerDay = car.getPricePerDay();
        this.pricePerKm = car.getPricePerKm();
        this.waiver = car.isWaiver();
        this.childSeats = car.getAvailableChildSeats();
    }
}
