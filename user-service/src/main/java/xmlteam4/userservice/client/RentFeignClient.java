package xmlteam4.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xmlteam4.userservice.model.DTO.Rent;
import xmlteam4.userservice.model.DTO.UserDTO;

import java.util.List;

@FeignClient(name = "rent-service")
public interface RentFeignClient {
    @GetMapping("/paid")
    List<Rent> getPaid();

}
