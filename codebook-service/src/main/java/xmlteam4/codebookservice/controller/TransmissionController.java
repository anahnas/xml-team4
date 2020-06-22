package xmlteam4.codebookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.codebookservice.model.Transmission;
import xmlteam4.codebookservice.service.TransmissionService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TransmissionController {

    @Autowired
    private TransmissionService transmissionService;

    @GetMapping(value = "/transmission/getAll")
    public ResponseEntity<List<Transmission>> getAll() {
        try {
            List<Transmission> transmission = this.transmissionService.getAll();
            return new ResponseEntity<>(transmission, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/transmission/getOne/{id}")
    public ResponseEntity<?> getTransmission(@PathVariable("id") Long id){
        Optional<Transmission> retVal = transmissionService.getTransmission(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/transmission/add")
    public ResponseEntity<?> addTransmission(@RequestBody Transmission transmission){
        Transmission retVal = this.transmissionService.addTransmission(transmission);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/transmission/deleteOne/{id}")
    public ResponseEntity deleteTransmission(@PathVariable("id") Long id) {

        try {
            Optional<Transmission> transmission = this.transmissionService.getTransmission(id);
            if (transmission != null) {
                this.transmissionService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
