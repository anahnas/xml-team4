package xmlteam4.codebookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xmlteam4.codebookservice.model.CarBrand;
import xmlteam4.codebookservice.model.DTO.CarBrandDTO;
import xmlteam4.codebookservice.model.DTO.CodebookDTO;
import xmlteam4.codebookservice.service.*;

@RestController
public class CodebookController {

    @Autowired
    private CarBrandService carBrandService;

    @GetMapping(value="/getCodebook/{carBrandId}/{carModelId}/{carClassId}/{fuelTypeId}/{transmissionId}")
    public CodebookDTO getCodebook(@PathVariable("carBrandId") Long carBrandId, @PathVariable("carModelId") Long carModelId,
                                   @PathVariable("carClassId") Long carClassId, @PathVariable("fuelTypeId") Long fuelTypeId,
                                   @PathVariable("transmissionId") Long transmissionId){
        CodebookDTO codebookDTO = new CodebookDTO();

        CarBrand carBrand = this.carBrandService.findById(carBrandId);
        CarBrandDTO carBrandDTO = new CarBrandDTO();
        carBrandDTO.setId(carBrand.getId());
        carBrandDTO.setName(carBrand.getName());


        codebookDTO.setCarBrandId(carBrandId);
        codebookDTO.setCarModelId(carModelId);
        codebookDTO.setCarClassId(carClassId);
        codebookDTO.setTransmissionId(transmissionId);
        codebookDTO.setFuelTypeId(fuelTypeId);

        codebookDTO.setCarBrandDTO(carBrandDTO);
        return codebookDTO;

    }



}
