package xmlteam4.rentservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDTOBasic {
    private Long id;
    private Long ownerId;
    private Double pricePerDay;
    private Double pricePerKm;
    private Double waiverPricePerDay;
}
