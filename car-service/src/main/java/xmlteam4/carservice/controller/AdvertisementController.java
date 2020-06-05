package xmlteam4.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.carservice.DTO.AdvertisementDTO;
import xmlteam4.carservice.DTO.CarDTO;
import xmlteam4.carservice.DTO.NewAdvertisementDTO;
import xmlteam4.carservice.model.Advertisement;
import xmlteam4.carservice.service.AdvertisementService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping(value="/advertisement/all")
    public ResponseEntity<List<AdvertisementDTO>> getAll() {
        try {
            List<AdvertisementDTO> advertisementDTOS = this.advertisementService.getAll();
            return new ResponseEntity<>(advertisementDTOS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/advertisement/new")
    public ResponseEntity<Long> newAdvertisement(@RequestBody NewAdvertisementDTO newAdvertisementDTO) {
        //provjeri po ulozi i counteru da li je user sa idijem i ulogom basic user dodao vise od 3 oglasa (imas counter)

            try {
            Long advertisementId = this.advertisementService.newAdvertisement(newAdvertisementDTO);
            return new ResponseEntity<>(advertisementId, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping(value = "/advertisement/{id}")
    public ResponseEntity<?> getAdvertisersAds(@PathVariable Long id) {
        try {
            List<Advertisement> ads = this.advertisementService.findAdvertisersAds(id);
            List<AdvertisementDTO> advertisementDTOS = new ArrayList<>();
            for (Advertisement ad : ads) {
                advertisementDTOS.add(new AdvertisementDTO(ad));
            }
            return new ResponseEntity(advertisementDTOS, HttpStatus.OK);

        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
