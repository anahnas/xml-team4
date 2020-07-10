package xmlteam4.carservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xmlteam4.carservice.DTO.RentDateDTO;

import java.util.ArrayList;

@FeignClient(name = "rent-service")
public interface RentFeignClient {
    @PostMapping(value = "/free")
    ArrayList<Long> getFree(@RequestBody RentDateDTO rentDateDTO);
}
