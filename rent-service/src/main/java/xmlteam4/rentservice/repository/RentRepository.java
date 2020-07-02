package xmlteam4.rentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlteam4.rentservice.model.Rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findAll();
    Optional<Rent> findById(Long id);

    @Query(value = "SELECT * FROM rent r WHERE r.status = 'PAID'", nativeQuery = true)
    List<Rent> getPaidRents();

    List<Rent> findByClientId(Long clientId);

}
