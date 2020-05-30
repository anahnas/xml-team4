package xmlteam4.codebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.codebookservice.model.CarBrand;

import java.util.List;
import java.util.Optional;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    @Override
    Optional<CarBrand> findById(Long aLong);

    @Override
    List<CarBrand> findAll();
}
