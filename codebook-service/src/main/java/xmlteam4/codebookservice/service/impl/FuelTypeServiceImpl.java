package xmlteam4.codebookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.codebookservice.model.FuelType;
import xmlteam4.codebookservice.repository.FuelTypeRepository;
import xmlteam4.codebookservice.service.FuelTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;


    @Override
    public List<FuelType> getAll() {
        return this.fuelTypeRepository.findAll();
    }

    @Override
    public Optional<FuelType> getFuelType(Long id) {
        return this.fuelTypeRepository.findById(id);

    }

    @Override
    public FuelType addFuelType(FuelType fuelType) {
        return this.fuelTypeRepository.save(fuelType);
    }

    @Override
    public void deleteById(Long id) {

        this.fuelTypeRepository.deleteById(id);
    }

    @Override
    public FuelType findById(Long fuelTypeId) {
        return this.fuelTypeRepository.getOne(fuelTypeId);
    }
}
