package xmlteam4.carservice.service;

import xmlteam4.carservice.DTO.AdvertisementDTO;
import xmlteam4.carservice.DTO.NewAdvertisementDTO;
import xmlteam4.carservice.model.Advertisement;

import java.util.List;

public interface AdvertisementService {

    List<AdvertisementDTO> getAll();
    Long newAdvertisement(NewAdvertisementDTO newAdvertisementDTO);
    List<Advertisement> findAdvertisersAds(Long advertiserId);
    int counter(Long id);
}
