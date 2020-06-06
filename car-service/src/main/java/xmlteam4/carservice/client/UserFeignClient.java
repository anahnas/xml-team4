package xmlteam4.carservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import xmlteam4.carservice.DTO.UserDTO;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("/getUser/{id}")
    UserDTO getUser(@PathVariable("id") String id);

}
