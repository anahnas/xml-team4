package xmlteam4.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.carservice.model.Car;

import java.util.ArrayList;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
    ArrayList<Car> findAll();
    void deleteById(Long id);
    Car save(Car car);
    ArrayList<Car> findByCarModelIdAndAndCarClassIdAndAndFuelTypeIdAndAndTransmissionId(Long carModelId, Long carClassId, Long fuelTypeId, Long transmissionId);
}
