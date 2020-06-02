package xmlteam4.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.carservice.model.Car;

import java.util.ArrayList;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
    ArrayList<Car> findAll();
    Car findCarById(Long id);
    void deleteById(Long id);
    Car save(Car car);
}
