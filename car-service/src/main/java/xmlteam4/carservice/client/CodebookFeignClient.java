package xmlteam4.carservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xmlteam4.carservice.DTO.CodebookDTO;
import xmlteam4.carservice.DTO.LocationDTO;
import xmlteam4.carservice.DTO.codebookh.CodebookDTOh;

import java.util.List;

@FeignClient(name = "codebook-service")
public interface CodebookFeignClient {
    @GetMapping("/getCodebook/{carBrandId}/{carModelId}/{carClassId}/{fuelTypeId}/{transmissionId}")
    CodebookDTO getCodebook(@PathVariable("carBrandId") Long carBrandId, @PathVariable("carModelId") Long carModelId,
                            @PathVariable("carClassId") Long carClassId, @PathVariable("fuelTypeId") Long fuelTypeId,
                            @PathVariable("transmissionId") Long transmissionId);

    @GetMapping("")
    CodebookDTOh getCodebookDTOh();


    @GetMapping("/getCodebook/{carBrandId}/{carModelId}/{carClassId}/{fuelTypeId}/{transmissionId}/{locationId}")
    CodebookDTO getCodebook(@PathVariable("carBrandId") Long carBrandId, @PathVariable("carModelId") Long carModelId,
                            @PathVariable("carClassId") Long carClassId, @PathVariable("fuelTypeId") Long fuelTypeId,
                            @PathVariable("transmissionId") Long transmissionId, @PathVariable ("locationId") Long locationId);


    //@GetMapping("/location")
    //List<LocationDTO> getAll();
}
