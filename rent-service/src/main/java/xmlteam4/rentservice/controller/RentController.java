package xmlteam4.rentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.rentservice.forms.RentForm;
import xmlteam4.rentservice.model.Bundle;
import xmlteam4.rentservice.model.Rent;
import xmlteam4.rentservice.service.RentService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        try {
            List<Rent> rents = this.rentService.getAll();
            return new ResponseEntity<>(rents, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/paid")
    public ResponseEntity<?> getPaid() {
        try {
            List<Rent> rents = this.rentService.getPaidRents();
            return new ResponseEntity<>(rents, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/rents")
    public ResponseEntity<?> postRents(@RequestBody List<RentForm> rentForms) {
        try {
            List<Rent> rents = rentService.postRents(rentForms);
            return new ResponseEntity<>(rents, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/bundle")
    public ResponseEntity<?> postBundle(@RequestBody List<RentForm> rentForms) {
        try {
            Bundle bundle = rentService.postBundle(rentForms);
            return new ResponseEntity<>(bundle, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> cancelRentRequest(@PathVariable("id") Long id){
        try {
            this.rentService.cancelRentRequest(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> acceptRentRequest(@PathVariable("id") Long id){
        try {
            this.rentService.acceptRentRequest(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
