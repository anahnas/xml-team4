package xmlteam4.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.carservice.DTO.AdvertisementDTO;
import xmlteam4.carservice.model.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Override
    List<Advertisement> findAll();

    @Override
    Optional<Advertisement> findById(Long aLong);
}
