package xmlteam4.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.model.CarRating;
import xmlteam4.carservice.model.RatingStatus;
import xmlteam4.carservice.repository.CarRatingRepository;

import java.util.List;

@Service
public class CarRatingService {

    @Autowired
    private CarRatingRepository carRatingRepository;


    public CarRating approveComment(CarRating carRating) {

        System.out.println(carRating.getRatingStatus());
        this.carRatingRepository.save(carRating);

        return carRating;
    }

    public List<CarRating> getAll(Long carId) {
        return this.carRatingRepository.findAllByCarId(carId);
    }

    public List<CarRating> getAllByUser(Long userId) {
        return this.carRatingRepository.findAllByUserId(userId);
    }

    public CarRating getOne(Long id) {
        return this.carRatingRepository.findById(id).get();
    }

    public CarRating addOne(CarRating carRating) {
        try{
            carRating.setRatingStatus(RatingStatus.PENDING);
            carRating = this.carRatingRepository.save(carRating);
            return this.carRatingRepository.getOne(carRating.getId());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public void deleteById(Long id) {
        this.carRatingRepository.deleteById(id);
    }

    public Double calculate(List<CarRating> carRatings) {
        Double retVal = 0.0;
        for(CarRating carRating : carRatings){
            retVal += carRating.getRating();
        }
        return retVal / carRatings.size();
    }
}
