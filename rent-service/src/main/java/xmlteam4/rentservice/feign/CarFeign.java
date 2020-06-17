package xmlteam4.rentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import xmlteam4.rentservice.dto.CarDTOBasic;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarFeign {
    @GetMapping("/basicCars")
    List<CarDTOBasic> basicCars();
}
