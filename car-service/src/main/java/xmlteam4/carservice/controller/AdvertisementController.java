package xmlteam4.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xmlteam4.carservice.DTO.AdvertisementDTO;
import xmlteam4.carservice.DTO.CarDTO;
import xmlteam4.carservice.DTO.NewAdvertisementDTO;
import xmlteam4.carservice.DTO.UserDTO;
import xmlteam4.carservice.client.UserFeignClient;
import xmlteam4.carservice.model.Advertisement;
import xmlteam4.carservice.service.AdvertisementService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping
    public ResponseEntity<List<AdvertisementDTO>> getAll() {
        try {
            List<AdvertisementDTO> advertisementDTOS = this.advertisementService.getAll();
            return new ResponseEntity<>(advertisementDTOS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Long> newAdvertisement(@RequestBody NewAdvertisementDTO newAdvertisementDTO) {
            try {
                UserDTO userDTO = this.userFeignClient.getUser(newAdvertisementDTO.getAdvertiserId());
                if(userDTO.getType().equals("BASIC_USER")) {
                    int counter = this.advertisementService.counter(newAdvertisementDTO.getAdvertiserId());
                    if(counter >= 3) {
                        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
                    }
                }
                Long advertisementId = this.advertisementService.newAdvertisement(newAdvertisementDTO);
                return new ResponseEntity<>(advertisementId, HttpStatus.CREATED);
        }   catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping(value = "/{id}")
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

    @PostMapping(value="/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) throws IOException {
        try{
            this.advertisementService.uploadImage(image);
        } catch( Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
