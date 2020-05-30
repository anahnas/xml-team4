package xmlteam4.rentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlteam4.rentservice.model.RentRequest;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<RentRequest, Long> {
    @Override
    List<RentRequest> findAll();

    @Override
    Optional<RentRequest> findById(Long id);

    @Query(value = "SELECT * FROM rent_request r WHERE r.status = 'PAID'", nativeQuery = true)
    List<RentRequest> getPaidRentReqs();
}
