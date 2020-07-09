package xmlteam4.codebookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.codebookservice.model.Location;
import xmlteam4.codebookservice.service.LocationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("location")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<Location>> getAll() {
        try {
            List<Location> locations = this.locationService.getAll();
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getLocation(@PathVariable("id") Long id){
        Optional<Location> retVal = locationService.getLocation(id);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addLocation(@RequestBody Location location){
        Location retVal = this.locationService.addLocation(location);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteLocation(@PathVariable("id") Long id) {

        try {
            Optional<Location> location = this.locationService.getLocation(id);
            if (location != null) {
                this.locationService.deleteById(id);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
