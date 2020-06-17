package xmlteam4.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.model.CarRating;
import xmlteam4.carservice.model.RatingStatus;
import xmlteam4.carservice.repository.CarRatingRepository;

@Service
public class CarRatingService {

    @Autowired
    private CarRatingRepository carRatingRepository;


    public CarRating approveComment(CarRating carRating) {

        System.out.println(carRating.getRatingStatus());
        this.carRatingRepository.save(carRating);

        return carRating;
    }

}
