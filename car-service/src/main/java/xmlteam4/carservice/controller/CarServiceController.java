package xml.team4.CarService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xml.team4.CarService.model.Car;
import xml.team4.CarService.service.CarService;
import xmlteam4.carservice.Forms.CarSearchForm;

import java.util.ArrayList;

@RestController
public class CarServiceController {

    @Autowired
    private CarService carService;

    @GetMapping(value="/cars/all")
    public ResponseEntity<?> getAllCars(){
        ArrayList<Car> retVal = carService.getAllCars();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/cars/{id}")
    public ResponseEntity<?> getCar(@PathVariable("id") Long id){
        Car retVal = carService.getCar(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/car")
    public ResponseEntity<?> addCar(@RequestBody Car car){
        Car retVal = this.carService.addCar(car);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value="/car/search")
    public ResponseEntity<?> searchCar(@RequestBody CarSearchForm carSearchForm){
        ArrayList<Car> retVal = this.carService.searchCars(carSearchForm);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value="/car")
    public ResponseEntity<?> editCar(@RequestBody Car car){
        Car retVal = this.carService.editCar(car);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value="/cars/{id}")
    public ResponseEntity<?> removeCar(@PathVariable("id") Long id){
        if(carService.removeCar(id))
            return new ResponseEntity<>("Deleted.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Error deleting car with id " + id +".", HttpStatus.BAD_REQUEST);
    }
}
