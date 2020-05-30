package xmlteam4.codebookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.codebookservice.model.CarBrand;
import xmlteam4.codebookservice.repository.CarBrandRepository;
import xmlteam4.codebookservice.service.CarBrandService;

import java.util.List;
import java.util.Optional;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public List<CarBrand> getAll() {
        return this.carBrandRepository.findAll();
    }

    @Override
    public Optional<CarBrand> getOneCarBrand(Long id) {
        return this.carBrandRepository.findById(id);
    }

    @Override
    public CarBrand addOne(CarBrand carBrand) {
        return this.carBrandRepository.save(carBrand);
    }

    @Override
    public void deleteById(Long id) {
        this.carBrandRepository.deleteById(id);
    }


}
