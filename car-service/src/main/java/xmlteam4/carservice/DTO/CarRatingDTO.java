package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.carservice.model.CarRating;

@Getter
@Setter
public class CarRatingDTO {
    private Long id;
    private Double rating;
    private Long userId;
    private Long carId;
    private String comment;
    private String username;
    private Enum rentingStatus;

    public CarRatingDTO() {
    }

    public CarRatingDTO(CarRating carRating) {
        if(carRating.getId() != null)
            this.id = carRating.getId();
        this.id = carRating.getId();
        this.rating = carRating.getRating();
        this.userId = carRating.getUserId();
        this.carId = carRating.getCarId();
        this.comment = carRating.getComment();
        this.rentingStatus = carRating.getRatingStatus();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
