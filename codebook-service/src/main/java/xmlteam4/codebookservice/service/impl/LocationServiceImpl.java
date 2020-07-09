package xmlteam4.codebookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.codebookservice.model.Location;
import xmlteam4.codebookservice.repository.LocationRepository;
import xmlteam4.codebookservice.service.LocationService;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAll() {
        return this.locationRepository.findAll();
    }

    @Override
    public Optional<Location> getLocation(Long id) {
        return this.locationRepository.findById(id);
    }

    @Override
    public Location addLocation(Location location) {
        return this.locationRepository.save(location);
    }

    @Override
    public void deleteById(Long id) {

        this.locationRepository.deleteById(id);

    }

    @Override
    public Location findById(Long locationId) {
        return this.locationRepository.getOne(locationId);
    }
}
