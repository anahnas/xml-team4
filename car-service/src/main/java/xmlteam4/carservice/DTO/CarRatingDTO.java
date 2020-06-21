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
    }

    @Override
    public String toString() {
        return super.toString();
    }
}