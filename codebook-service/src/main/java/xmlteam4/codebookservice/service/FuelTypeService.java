package xmlteam4.codebookservice.service;

import xmlteam4.codebookservice.model.FuelType;

import java.util.List;
import java.util.Optional;

public interface FuelTypeService {

    List<FuelType> getAll();

    Optional<FuelType> getFueltType(Long id);

    FuelType addFuelType(FuelType fuelType);

    void deleteById(Long id);
}
