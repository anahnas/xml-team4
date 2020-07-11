package xmlteam4.carservice.service;

import org.springframework.web.multipart.MultipartFile;
import xmlteam4.carservice.DTO.AdvertisementDTO;
import xmlteam4.carservice.DTO.NewAdvertisementDTO;
import xmlteam4.carservice.model.Advertisement;

import java.io.IOException;
import java.util.List;

public interface AdvertisementService {

    List<AdvertisementDTO> getAll();
    Advertisement getAdvertisement(Long id);
    Long newAdvertisement(NewAdvertisementDTO newAdvertisementDTO);
    List<Advertisement> findAdvertisersAds(Long advertiserId);
    int counter(Long advertiserId);
    void uploadImage(MultipartFile image) throws IOException;
}
