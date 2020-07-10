package xmlteam4.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.DTO.CarRatingDTO;
import xmlteam4.carservice.DTO.UserDTO;
import xmlteam4.carservice.client.UserFeignClient;
import xmlteam4.carservice.model.CarRating;
import xmlteam4.carservice.model.RatingStatus;
import xmlteam4.carservice.repository.CarRatingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarRatingService {

    @Autowired
    private CarRatingRepository carRatingRepository;

    @Autowired
    UserFeignClient userFeignClient;

    public CarRating approveComment(CarRating carRating) {

        System.out.println(carRating.getRatingStatus());
        this.carRatingRepository.save(carRating);

        return carRating;
    }

    public List<CarRating> getAll(Long carId) {
        if(carId != 0)
            return this.carRatingRepository.findAllByCarId(carId);
        else {
            ArrayList<CarRating> carRatings = (ArrayList<CarRating>) this.carRatingRepository.findAll();
            ArrayList<CarRating> newCarRating = new ArrayList<CarRating>();
            for(CarRating cr: carRatings) {
                if(cr.getRatingStatus().equals(RatingStatus.PENDING))
                    newCarRating.add(cr);
            }

            return newCarRating;
        }

    }

    public ArrayList<CarRatingDTO> getAllDTOs(Long carId) {
        //if ratings from certain car are requested
        if(carId != 0){
            List<CarRating> carRatings = this.carRatingRepository.findAllByCarId(carId);
            ArrayList<CarRatingDTO> carRatingDTOs = new ArrayList<>();
            for(CarRating carRating : carRatings){
                if(carRating.getRatingStatus() != RatingStatus.PENDING){
                    CarRatingDTO carRatingDTO = new CarRatingDTO(carRating);
                    System.out.println(carRating.getComment());
                    try{
                        UserDTO userDTO = this.userFeignClient.getUser(carRating.getUserId());
                        carRatingDTO.setUsername(userDTO.getUsername());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("username: " + carRatingDTO.getUsername());
                    System.out.println("user id: " + carRating.getUserId().toString());
                    carRatingDTOs.add(carRatingDTO);
                }
            }
            return carRatingDTOs;
        }
        //or else get all pending
        ArrayList<CarRating> carRatings = (ArrayList<CarRating>) this.carRatingRepository.findAll();
        ArrayList<CarRatingDTO> carRatingDTOs = new ArrayList<>();
        for(CarRating cr: carRatings) {
            if(cr.getRatingStatus().equals(RatingStatus.PENDING)){
                CarRatingDTO carRatingDTO = new CarRatingDTO(cr);
                try{
                    carRatingDTO.setUsername(this.userFeignClient.getUser(cr.getUserId()).getUsername());
                } catch (Exception e){
                    e.printStackTrace();
                }
                carRatingDTOs.add(carRatingDTO);
            }
        }
        return carRatingDTOs;

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
