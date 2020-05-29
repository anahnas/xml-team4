package xmlteam4.codebookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.codebookservice.model.FuelType;
import xmlteam4.codebookservice.service.FuelTypeService;

import java.util.List;
import java.util.Optional;

@RestController
public class FuelTypeController {
    @Autowired
    private FuelTypeService fuelTypeService;

    @GetMapping(value = "/fuelType/getAll")
    public ResponseEntity<List<FuelType>> getAll() {
        try {
            List<FuelType> fuelTypes = this.fuelTypeService.getAll();
            return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/fuelType/getOne/{id}")
    public ResponseEntity<?> getFuelType(@PathVariable("id") Long id){
        Optional<FuelType> retVal = fuelTypeService.getFueltType(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/fuelType/add")
    public ResponseEntity<?> addFuelType(@RequestBody FuelType fuelType){
        FuelType retVal = this.fuelTypeService.addFuelType(fuelType);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/fuelType/deleteOne/{id}")
    public ResponseEntity deleteFuelType(@PathVariable("id") Long id) {

        try {
            Optional<FuelType> fuelType = this.fuelTypeService.getFueltType(id);
            if (fuelType != null) {
                this.fuelTypeService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
