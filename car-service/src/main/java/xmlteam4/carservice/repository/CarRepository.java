package xml.team4.CarService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.team4.CarService.model.Car;

import java.util.ArrayList;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
    ArrayList<Car> findAll();
    void deleteById(Long id);
    Car save(Car car);
    ArrayList<Car> findByCarModelIdAndAndCarClassIdAndAndFuelTypeIdAndAndTransmissionId(Long carModelId, Long carClassId, Long fuelTypeId, Long transmissionId);
}
