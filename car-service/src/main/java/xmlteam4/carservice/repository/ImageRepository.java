package xmlteam4.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.carservice.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
