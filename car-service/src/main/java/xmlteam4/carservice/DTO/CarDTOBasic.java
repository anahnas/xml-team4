package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.carservice.model.Car;

@Getter
@Setter
@NoArgsConstructor
public class CarDTOBasic {
    private Long id;
    private Long ownerId;
    private Double pricePerDay;
    private Double pricePerKm;
    private Double waiverPricePerDay;

    public CarDTOBasic(Car car) {
        this.id = car.getId();
        this.ownerId = car.getOwnerId();
        this.pricePerDay = car.getPricePerDay();
        this.pricePerKm = car.getPricePerKm();
        this.waiverPricePerDay = car.getWaiverPricePerDay();
    }
}
