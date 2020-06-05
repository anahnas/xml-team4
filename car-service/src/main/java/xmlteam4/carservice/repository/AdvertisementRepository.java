package xmlteam4.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlteam4.carservice.model.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Override
    List<Advertisement> findAll();

    @Override
    Optional<Advertisement> findById(Long aLong);

    @Query(value = "select a from Advertisement a where a.advertiserId = ?1 ")
    List<Advertisement> findAdsByAdvertiserId(Long advertiserId);

    @Query(value = "select count(a) from Advertisement a where a.advertiserId = ?1 ")
    int counter(Long id);
}
