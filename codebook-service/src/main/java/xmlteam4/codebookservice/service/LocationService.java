package xmlteam4.codebookservice.service;

import xmlteam4.codebookservice.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    List<Location> getAll();

    Optional<Location> getLocation(Long id);

    Location addLocation(Location location);

    void deleteById(Long id);

    Location findById(Long locationId);

}
