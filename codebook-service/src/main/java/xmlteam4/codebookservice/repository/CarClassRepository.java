package xmlteam4.codebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.codebookservice.model.CarClass;

import java.util.List;
import java.util.Optional;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {

    @Override
    Optional<CarClass> findById(Long aLong);

    @Override
    List<CarClass> findAll();
}
