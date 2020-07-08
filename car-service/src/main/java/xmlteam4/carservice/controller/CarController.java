package xmlteam4.carservice.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.carservice.DTO.TempCarDTO;
import xmlteam4.carservice.Forms.CarPrettyForm;
import xmlteam4.carservice.Forms.CarSearchForm;
import xmlteam4.carservice.model.Car;
import xmlteam4.carservice.model.CarRating;
import xmlteam4.carservice.model.RatingStatus;
import xmlteam4.carservice.model.Rental;
import xmlteam4.carservice.service.CarService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("car")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<?> getAllCars(){
        ArrayList<TempCarDTO> retVal = carService.getAllCarDTOs();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCar(@PathVariable("id") Long id){
        TempCarDTO retVal = carService.getCarDTO(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car car){
        Car retVal = this.carService.addCar(car);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value="/search")
    public ResponseEntity<?> searchCar(@RequestBody CarSearchForm carSearchForm){
        ArrayList<Car> retVal = this.carService.searchCars(carSearchForm);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> editCar(@RequestBody Car car){
        Car retVal = this.carService.editCar(car);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> removeCar(@PathVariable("id") Long id){
        if(carService.removeCar(id))
            return new ResponseEntity<>("Deleted.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Error deleting car with id " + id +".", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value="/notAvailable")
    public ResponseEntity<?> notAvailable(@RequestBody Rental rental) {
        Rental r = this.carService.blockCar(rental);
        if( r == null )
            return new ResponseEntity<>("Car blocking error!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>( "Car is not available any more.", HttpStatus.OK);
    }

    @GetMapping(value = "/basic")
    public ResponseEntity<?> basicCars() {
        return new ResponseEntity<>(carService.basicCars(), HttpStatus.OK);
    }

    @GetMapping(value = "/basic/{id}")
    public ResponseEntity<?> basicCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.basicCar(id), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty")
    public ResponseEntity<?> prettyCars(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(carService.prettyCars(ids), HttpStatus.OK);
    }
}
