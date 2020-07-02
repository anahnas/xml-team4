package xmlteam4.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xmlteam4.userservice.model.DTO.CarDTOBasic;

@FeignClient(name = "car-service")
public interface CarFeignClient {
    @GetMapping(value = "car/basic/{id}")
    CarDTOBasic basicCar(@PathVariable Long id);
}
