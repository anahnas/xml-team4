package xmlteam4.codebookservice.service;

import xmlteam4.codebookservice.model.CarClass;

import java.util.List;
import java.util.Optional;

public interface CarClassService {

    List<CarClass> getAll();

    Optional<CarClass> getOneCarClass(Long id);

    CarClass addOne(CarClass carClass);

    void deleteById(Long id);
}
