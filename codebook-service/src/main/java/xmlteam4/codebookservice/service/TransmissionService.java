package xmlteam4.codebookservice.service;

import xmlteam4.codebookservice.model.Transmission;

import java.util.List;
import java.util.Optional;

public interface TransmissionService {

    List<Transmission> getAll();

    Optional<Transmission> getTransmission(Long id);

    Transmission addTransmission(Transmission transmission);

    void deleteById(Long id);
}
