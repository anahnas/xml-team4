package xmlteam4.rentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xmlteam4.rentservice.dto.CarDTOBasic;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarFeign {
    @GetMapping("/car/basic")
    List<CarDTOBasic> basicCars();

    @GetMapping(value = "/car/basic/{id}")
    CarDTOBasic getOneBasic(@PathVariable("id") Long id);

    @GetMapping("/car/basic/{id}")
    CarDTOBasic basicCar(@PathVariable Long id);

    @GetMapping(value = "/car/basic/user/{id}")
    List<CarDTOBasic> getAllCarsByOwner(@PathVariable Long id);

    @GetMapping(value="/car/owner/{id}")
    Long getOwner(@PathVariable Long id);
}
