package xmlteam4.codebookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.codebookservice.model.Transmission;
import xmlteam4.codebookservice.repository.TransmissionRepository;
import xmlteam4.codebookservice.service.TransmissionService;

import java.util.List;
import java.util.Optional;

@Service
public class TransmissionServiceImpl implements TransmissionService {

    @Autowired
    private TransmissionRepository transmissionRepository;

    @Override
    public List<Transmission> getAll() {
        return this.transmissionRepository.findAll();
    }

    @Override
    public Optional<Transmission> getTransmission(Long id) {
        return this.transmissionRepository.findById(id);
    }

    @Override
    public Transmission addTransmission(Transmission transmission) {
        return this.transmissionRepository.save(transmission);
    }

    @Override
    public void deleteById(Long id) {

        this.transmissionRepository.deleteById(id);

    }

    @Override
    public Transmission findById(Long transmissionId) {
        return this.transmissionRepository.getOne(transmissionId);
    }
}
