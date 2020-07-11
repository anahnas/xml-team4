package xmlteam4.rentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xmlteam4.rentservice.model.Rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findAll();

    Optional<Rent> findById(Long id);

    List<Rent> findAllByCarId(Long id);

    @Query(value = "SELECT * FROM rent r WHERE r.status = 'PAID'", nativeQuery = true)
    List<Rent> getPaidRents();

    @Query("SELECT r FROM Rent r WHERE NOT (?1 > r.startDate and ?2 < r.endDate) and NOT(?1 > r.startDate and ?1 < r.endDate) and NOT (?2 > r.startDate and ?2 < r.endDate) and NOT (?1 < r.startDate and ?2 > r.endDate)")
    ArrayList<Rent> findFree(LocalDateTime startDate, LocalDateTime endDate);

    List<Rent> findByClientId(Long clientId);

    @Modifying
    @Query("DELETE FROM Rent r where r.id in ?1")
    void deleteRentsWithIds(List<Long> ids);

}
