package xmlteam4.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.carservice.DTO.CarSearchDTO;
import xmlteam4.carservice.DTO.TempCarDTO;
import xmlteam4.carservice.model.Car;
import xmlteam4.carservice.service.CarService;

import java.io.IOException;
import java.util.ArrayList;

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
    public ResponseEntity<?> searchCar(@RequestBody CarSearchDTO carSearchDTO){
        ArrayList<TempCarDTO> retVal = this.carService.searchCars(carSearchDTO);
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

    @GetMapping(value = "/basic")
    public ResponseEntity<?> basicCars() {
        return new ResponseEntity<>(carService.basicCars(), HttpStatus.OK);
    }

    @GetMapping(value = "basic/user/{id}")
    public ResponseEntity<?> getAllCarsByOwner(@PathVariable Long id){
        return new ResponseEntity<>(carService.basicCarsByOwner(id), HttpStatus.OK);
    }

    @GetMapping(value = "/basic/{id}")
    public ResponseEntity<?> basicCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.basicCar(id), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty")
    public ResponseEntity<?> prettyCars(@RequestParam String ids) {
        return new ResponseEntity<>(carService.prettyCars(ids), HttpStatus.OK);
    }

    @GetMapping(value="/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getCarImage(@PathVariable("id") Long id) throws IOException {
        return this.carService.getCarImage(id);
    }

    @GetMapping(value="/owner/{id}")
    public Long getOwner(@PathVariable Long id) {
        try{
            return this.carService.getOwner(id);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
