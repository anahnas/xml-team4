package xmlteam4.rentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xmlteam4.rentservice.model.RentRequest;
import xmlteam4.rentservice.service.RentService;

import java.util.List;

@RestController
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping(value = "/rent/getAll")
    public ResponseEntity<List<RentRequest>> getAll() {

        try {
            List<RentRequest> rentRequests = this.rentService.getAll();

            return new ResponseEntity<>(rentRequests, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/rent/getPaid")
    public ResponseEntity<List<RentRequest>> getPaid() {

        try {
            List<RentRequest> rentRequests = this.rentService.getPaidRentReqs();

            return new ResponseEntity<>(rentRequests, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/rent/create")
    public ResponseEntity<?> createRentRequest(@RequestBody RentRequest rentRequest) {
        try {
            this.rentService.createRentReq(rentRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
