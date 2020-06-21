package xmlteam4.carservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.carservice.DTO.CarRatingDTO;
import xmlteam4.carservice.model.CarRating;
import xmlteam4.carservice.model.RatingStatus;
import xmlteam4.carservice.service.CarRatingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("rating")
@CrossOrigin(origins = "http://localhost:4200")
public class CarRatingController {

    @Autowired
    private CarRatingService carRatingService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader ("carId") Long carId) {
        try {
            List<CarRating> carRatings = this.carRatingService.getAll(carId);
            List<CarRatingDTO> CarRatingDTOs = new ArrayList<>();
            for(CarRating carRating : carRatings){
                CarRatingDTOs.add(new CarRatingDTO(carRating));
            }
            return new ResponseEntity<>(CarRatingDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCarRating(@PathVariable Long id){
        try{
            CarRating retVal = carRatingService.getOne(id);
            CarRatingDTO retValDTO = new CarRatingDTO(retVal);
            return new ResponseEntity<>(retValDTO, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Resource not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addCarRating(@RequestBody CarRatingDTO carRatingDTO){
        CarRating retVal = this.carRatingService.addOne(new CarRating(carRatingDTO));
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping
    public ResponseEntity<?> manageComment(@RequestBody CarRating carRating) {
        CarRating carR = this.carRatingService.approveComment(carRating);
        if( carRating == null )
            return new ResponseEntity<>("Comment error!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>( "Comment approved or rejected!", HttpStatus.OK);
    }

    @GetMapping(value="/rate")
    public ResponseEntity<?> getRate(@RequestHeader ("carId") Long carId) {
        try {
            List<CarRating> carRatings = this.carRatingService.getAll(carId);
            return new ResponseEntity<>(this.carRatingService.calculate(carRatings), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}