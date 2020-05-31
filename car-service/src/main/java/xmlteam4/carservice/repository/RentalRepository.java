package xmlteam4.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlteam4.carservice.model.Rental;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    ArrayList<Rental> findByCarCalendarId(Long id);
    @Query("SELECT r FROM Rental r WHERE NOT (?1 > r.startDate and ?2 < r.endDate) and NOT(?1 > r.startDate and ?1 < r.endDate) and NOT (?2 > r.startDate and ?2 < r.endDate) and NOT (?1 < r.startDate and ?2 > r.endDate)")
    ArrayList<Rental> findFree(Date startDate, Date endDate);
}
