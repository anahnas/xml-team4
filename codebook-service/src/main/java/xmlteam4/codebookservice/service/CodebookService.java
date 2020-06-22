package xmlteam4.codebookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.codebookservice.dto.CodebookDTO;
import xmlteam4.codebookservice.model.*;

import java.util.List;

@Service
public class CodebookService {
    @Autowired
    private CarBrandService carBrandService;
    @Autowired
    private CarClassService carClassService;
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private FuelTypeService fuelTypeService;
    @Autowired
    private TransmissionService transmissionService;

    public CodebookDTO getCodebook() {
        List<CarBrand> carBrands = carBrandService.getAll();
        List<CarClass> carClasses = carClassService.getAll();
        List<CarModel> carModels = carModelService.getAll();
        List<FuelType> fuelTypes = fuelTypeService.getAll();
        List<Transmission> transmissions = transmissionService.getAll();

        return new CodebookDTO(carBrands, carClasses, carModels, fuelTypes, transmissions);
    }
}
