package xmlteam4.rentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xmlteam4.rentservice.dto.UserDTO;

@FeignClient(name = "user-service")
public interface UserFeign {
    @GetMapping("/user/{id}")
    UserDTO getUser(@PathVariable("id") Long id);
}
