package xmlteam4.carservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.carservice.model.CarRating;

import java.util.List;
import java.util.Optional;

public interface CarRatingRepository extends JpaRepository<CarRating, Long> {
    @Override
    Optional<CarRating> findById(Long aLong);
    @Override
    List<CarRating> findAll();
    @Override
    void deleteById(Long id);
    @Override
    CarRating save(CarRating carRating);

    @Override
    void delete(CarRating carRating);

    List<CarRating> findAllByCarId(Long id);

    List<CarRating> findAllByUserId(Long id);

}