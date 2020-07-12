package xmlteam4.carservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import xmlteam4.carservice.DTO.UserDTO;
import xmlteam4.carservice.model.RoleTypes;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("user/{id}")
    UserDTO getUser(@PathVariable("id") Long id);
    @GetMapping(value = "/user/authority/{id}")
    RoleTypes checkAuthority(@PathVariable Long id);

}
