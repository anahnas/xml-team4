package xmlteam4.codebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.codebookservice.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository  extends JpaRepository<Location, Long> {

    @Override
    Optional<Location> findById(Long id);

    @Override
    List<Location> findAll();

    @Override
    void deleteById(Long id);

    Location save(Location location);
}
