package xmlteam4.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlteam4.carservice.model.CarCalendar;
import xmlteam4.carservice.model.Rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public interface CarCalendarRepository extends JpaRepository<CarCalendar, Long> {
    Optional<CarCalendar> findById(Long id);
    CarCalendar findByCarId(Long carId);
    CarCalendar save(CarCalendar carCalendar);
}
