package xmlteam4.codebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.codebookservice.model.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    @Override
    Optional<CarModel> findById(Long aLong);

    @Override
    List<CarModel> findAll();

    List<CarModel> findAllByCarBrandId(Long id);
}
