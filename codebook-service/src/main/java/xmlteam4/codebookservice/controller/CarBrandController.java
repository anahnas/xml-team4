package xmlteam4.codebookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.codebookservice.model.CarBrand;
import xmlteam4.codebookservice.service.CarBrandService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carBrand")
@CrossOrigin(origins = "http://localhost:4200")
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;

    @GetMapping
    public ResponseEntity<List<CarBrand>> getAll() {
        try {
            List<CarBrand> fuelTypes = this.carBrandService.getAll();
            return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCarBrand(@PathVariable("id") Long id){
        Optional<CarBrand> retVal = carBrandService.getOneCarBrand(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/add")
    public ResponseEntity<?> addCarBrand(@RequestBody CarBrand carBrand){
        CarBrand retVal = this.carBrandService.addOne(carBrand);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCarBrand(@PathVariable("id") Long id) {

        try {
            Optional<CarBrand> carBrand = this.carBrandService.getOneCarBrand(id);
            if (carBrand != null) {
                this.carBrandService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
