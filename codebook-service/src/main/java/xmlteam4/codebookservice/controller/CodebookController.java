package xmlteam4.codebookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xmlteam4.codebookservice.dto.LocationDTO;
import xmlteam4.codebookservice.model.*;
import xmlteam4.codebookservice.model.DTO.*;
import xmlteam4.codebookservice.service.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CodebookController {

    @Autowired
    private CarBrandService carBrandService;
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private CarClassService carClassService;
    @Autowired
    private FuelTypeService fuelTypeService;
    @Autowired
    private TransmissionService transmissionService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CodebookService codebookService;


    @GetMapping(value="/getCodebook/{carBrandId}/{carModelId}/{carClassId}/{fuelTypeId}/{transmissionId}")
    public CodebookDTO getCodebook(@PathVariable("carBrandId") Long carBrandId, @PathVariable("carModelId") Long carModelId,
                                   @PathVariable("carClassId") Long carClassId, @PathVariable("fuelTypeId") Long fuelTypeId,
                                   @PathVariable("transmissionId") Long transmissionId){
        CodebookDTO codebookDTO = new CodebookDTO();

        CarBrand carBrand = this.carBrandService.findById(carBrandId);
        CarBrandDTO carBrandDTO = new CarBrandDTO();
        carBrandDTO.setId(carBrand.getId());
        carBrandDTO.setName(carBrand.getName());

        CarModel carModel = this.carModelService.findById(carModelId);
        CarModelDTO carModelDTO = new CarModelDTO();
        carModelDTO.setId(carModel.getId());
        carModelDTO.setName(carModel.getName());

        CarClass carClass = this.carClassService.findById(carClassId);
        CarClassDTO carClassDTO = new CarClassDTO();
        carClassDTO.setId(carClass.getId());
        carClassDTO.setCarClass(carClass.getName());

        FuelType fuelType = this.fuelTypeService.findById(fuelTypeId);
        FuelTypeDTO fuelTypeDTO = new FuelTypeDTO();
        fuelTypeDTO.setId(fuelType.getId());
        fuelTypeDTO.setType(fuelType.getName());

        Transmission transmission = this.transmissionService.findById(transmissionId);
        TransmissionDTO transmissionDTO = new TransmissionDTO();
        transmissionDTO.setId(transmission.getId());
        transmissionDTO.setType(transmission.getName());

        codebookDTO.setCarBrandId(carBrandId);
        codebookDTO.setCarModelId(carModelId);
        codebookDTO.setCarClassId(carClassId);
        codebookDTO.setTransmissionId(transmissionId);
        codebookDTO.setFuelTypeId(fuelTypeId);

        codebookDTO.setCarBrandDTO(carBrandDTO);
        codebookDTO.setCarModelDTO(carModelDTO);
        codebookDTO.setCarClassDTO(carClassDTO);
        codebookDTO.setFuelTypeDTO(fuelTypeDTO);
        codebookDTO.setTransmissionDTO(transmissionDTO);
        return codebookDTO;
    }

    @GetMapping(value="/getCodebook/{carBrandId}/{carModelId}/{carClassId}/{fuelTypeId}/{transmissionId}/{locationId}")
    public CodebookDTO getCodebook(@PathVariable("carBrandId") Long carBrandId, @PathVariable("carModelId") Long carModelId,
                                   @PathVariable("carClassId") Long carClassId, @PathVariable("fuelTypeId") Long fuelTypeId,
                                   @PathVariable("transmissionId") Long transmissionId, @PathVariable ("locationId") Long locationId){
        CodebookDTO codebookDTO = new CodebookDTO();

        CarBrand carBrand = this.carBrandService.findById(carBrandId);
        CarBrandDTO carBrandDTO = new CarBrandDTO();
        carBrandDTO.setId(carBrand.getId());
        carBrandDTO.setName(carBrand.getName());

        CarModel carModel = this.carModelService.findById(carModelId);
        CarModelDTO carModelDTO = new CarModelDTO();
        carModelDTO.setId(carModel.getId());
        carModelDTO.setName(carModel.getName());

        CarClass carClass = this.carClassService.findById(carClassId);
        CarClassDTO carClassDTO = new CarClassDTO();
        carClassDTO.setId(carClass.getId());
        carClassDTO.setCarClass(carClass.getName());

        FuelType fuelType = this.fuelTypeService.findById(fuelTypeId);
        FuelTypeDTO fuelTypeDTO = new FuelTypeDTO();
        fuelTypeDTO.setId(fuelType.getId());
        fuelTypeDTO.setType(fuelType.getName());

        Transmission transmission = this.transmissionService.findById(transmissionId);
        TransmissionDTO transmissionDTO = new TransmissionDTO();
        transmissionDTO.setId(transmission.getId());
        transmissionDTO.setType(transmission.getName());

        Location location = this.locationService.findById(locationId);
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());

        codebookDTO.setCarBrandId(carBrandId);
        codebookDTO.setCarModelId(carModelId);
        codebookDTO.setCarClassId(carClassId);
        codebookDTO.setTransmissionId(transmissionId);
        codebookDTO.setFuelTypeId(fuelTypeId);
        codebookDTO.setLocationId(locationId);

        codebookDTO.setCarBrandDTO(carBrandDTO);
        codebookDTO.setCarModelDTO(carModelDTO);
        codebookDTO.setCarClassDTO(carClassDTO);
        codebookDTO.setFuelTypeDTO(fuelTypeDTO);
        codebookDTO.setTransmissionDTO(transmissionDTO);
        codebookDTO.setLocationDTO(locationDTO);
        return codebookDTO;
    }

    @GetMapping
    public ResponseEntity<?> getCodebookDTO() {
        try {
            return new ResponseEntity<>(codebookService.getCodebook(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
