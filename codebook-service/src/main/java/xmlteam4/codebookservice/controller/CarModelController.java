package xmlteam4.codebookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.codebookservice.model.CarModel;
import xmlteam4.codebookservice.service.CarModelService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carModel")
@CrossOrigin(origins = "http://localhost:4200")
public class CarModelController {
    @Autowired
    private CarModelService carModelService;

    @GetMapping
    public ResponseEntity<List<CarModel>> getAll() {
        try {
            List<CarModel> fuelTypes = this.carModelService.getAll();
            return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getOneCarModel(@PathVariable("id") Long id){
        Optional<CarModel> retVal = carModelService.getOneCarModel(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/carBrand/{id}")
    public ResponseEntity<List<CarModel>> getByCarBrand(@PathVariable("id") Long id) {
        try {
            List<CarModel> fuelTypes = this.carModelService.getByCarBrand(id);
            return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCarModel(@PathVariable("id") Long id) {

        try {
            Optional<CarModel> carModel = this.carModelService.getOneCarModel(id);
            if (carModel != null) {
                this.carModelService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addCarModel(@RequestBody CarModel carModel){
        CarModel retVal = this.carModelService.addOne(carModel);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
