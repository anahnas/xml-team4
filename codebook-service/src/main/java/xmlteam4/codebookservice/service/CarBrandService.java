package xmlteam4.codebookservice.service;

import xmlteam4.codebookservice.model.CarBrand;

import java.util.List;
import java.util.Optional;

public interface CarBrandService {

    List<CarBrand> getAll();

    Optional<CarBrand> getOneCarBrand(Long id);

    CarBrand addOne(CarBrand carBrand);

    void deleteById(Long id);

    CarBrand findById(Long carBrandId);
}
