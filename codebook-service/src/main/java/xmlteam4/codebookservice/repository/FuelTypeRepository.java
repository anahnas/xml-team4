package xmlteam4.codebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.codebookservice.model.FuelType;

import java.util.ArrayList;
import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

    @Override
    Optional<FuelType> findById(Long id);

    ArrayList<FuelType> findAll();
    void deleteById(Long id);
    FuelType save(FuelType fuelType);
}
