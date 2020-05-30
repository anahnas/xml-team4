package xmlteam4.codebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.codebookservice.model.Transmission;

import java.util.List;
import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {

    @Override
    Optional<Transmission> findById(Long id);

    @Override
    List<Transmission> findAll();

    @Override
    void deleteById(Long id);

    Transmission save(Transmission transmission);
}
