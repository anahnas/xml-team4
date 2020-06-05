package xmlteam4.codebookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.codebookservice.model.CarClass;
import xmlteam4.codebookservice.repository.CarClassRepository;
import xmlteam4.codebookservice.service.CarClassService;

import java.util.List;
import java.util.Optional;

@Service
public class CarClassServiceImpl implements CarClassService {


    @Autowired
    private CarClassRepository carClassRepository;

    @Override
    public List<CarClass> getAll() {
        return this.carClassRepository.findAll();
    }

    @Override
    public Optional<CarClass> getOneCarClass(Long id) {
        return this.carClassRepository.findById(id);
    }

    @Override
    public CarClass addOne(CarClass carClass) {
        return this.carClassRepository.save(carClass);
    }

    @Override
    public void deleteById(Long id) {
        this.carClassRepository.deleteById(id);

    }

    @Override
    public CarClass findById(Long carClassId) {
        return this.carClassRepository.getOne(carClassId);
    }
}
