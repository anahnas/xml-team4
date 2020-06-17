package xmlteam4.carservice.model;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.carservice.model.RatingStatus;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CarRating {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Double rating;
    private Long userId;
    private Long carId;
    private String comment;
    private RatingStatus ratingStatus;

    public CarRating() {
    }

    public CarRating(CarRating carRatingDTO) {
        System.out.println(carRatingDTO.getId() + " " + userId);
        this.id = carRatingDTO.getId();
        this.rating = carRatingDTO.getRating();
        this.userId = userId;
        this.carId = carId;
        this.comment = carRatingDTO.getComment();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}