package xmlteam4.rentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xmlteam4.rentservice.dto.CarDTOBasic;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarFeign {
    @GetMapping("/car/basic")
    List<CarDTOBasic> basicCars();

    @GetMapping("/car/basic/{id}")
    CarDTOBasic basicCar(@PathVariable Long id);
}
