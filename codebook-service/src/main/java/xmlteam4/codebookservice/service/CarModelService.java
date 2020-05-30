package xmlteam4.codebookservice.service;

import xmlteam4.codebookservice.model.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarModelService {

    List<CarModel> getAll();

    Optional<CarModel> getOneCarModel(Long id);

    CarModel addOne(CarModel carModel);

    void deleteById(Long id);
}