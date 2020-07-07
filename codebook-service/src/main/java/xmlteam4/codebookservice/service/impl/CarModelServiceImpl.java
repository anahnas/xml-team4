package xmlteam4.codebookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.codebookservice.model.CarModel;
import xmlteam4.codebookservice.repository.CarModelRepository;
import xmlteam4.codebookservice.service.CarModelService;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Override
    public List<CarModel> getAll() {
        return this.carModelRepository.findAll();

    }

    @Override
    public Optional<CarModel> getOneCarModel(Long id) {
        return this.carModelRepository.findById(id);
    }

    @Override
    public List<CarModel> getByCarBrand(Long id) {
        return this.carModelRepository.findAllByCarBrandId(id);
    }

    @Override
    public CarModel addOne(CarModel carModel) {
        return this.carModelRepository.save(carModel);
    }

    @Override
    public void deleteById(Long id) {
        this.carModelRepository.deleteById(id);
    }

    @Override
    public CarModel findById(Long carModelId) {
        return this.carModelRepository.getOne(carModelId);
    }


}
