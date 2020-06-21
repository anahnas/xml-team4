package xmlteam4.carservice.model;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.carservice.DTO.CarRatingDTO;
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

    public CarRating(CarRatingDTO carRatingDTO) {
        System.out.println(carRatingDTO.getId() + " " + userId);
        this.id = carRatingDTO.getId();
        this.rating = carRatingDTO.getRating();
        this.userId = carRatingDTO.getUserId();
        this.carId = carRatingDTO.getCarId();
        this.comment = carRatingDTO.getComment();
    }

    @Override
    public String toString() {
        return "CarRating{" +
                "rating=" + rating +
                ", userId=" + userId +
                ", carId=" + carId +
                ", comment='" + comment + '\'' +
                ", ratingStatus=" + ratingStatus +
                '}';
    }
}