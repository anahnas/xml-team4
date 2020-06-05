package xmlteam4.carservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.DTO.*;
import xmlteam4.carservice.client.CodebookFeignClient;
import xmlteam4.carservice.model.Advertisement;
import xmlteam4.carservice.repository.AdvertisementRepository;
import xmlteam4.carservice.service.AdvertisementService;
import xmlteam4.carservice.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CodebookFeignClient codebookFeignClient;

    @Autowired
    private CarService carService;

    @Override
    public List<AdvertisementDTO> getAll() {
        List<AdvertisementDTO> advertisementDTOS = new ArrayList<>();
        List<Advertisement> advertisements = this.advertisementRepository.findAll();
        return getAdDTOS(advertisementDTOS, advertisements);
    }


    private List<AdvertisementDTO> getAdDTOS(List<AdvertisementDTO> advertisementDTOS, List<Advertisement> advertisements){
        for(Advertisement advertisement : advertisements) {

            CodebookDTO codebookDTO = this.codebookFeignClient.getCodebook(advertisement.getCar().getCarBrandId(), advertisement.getCar().getCarModelId(), advertisement.getCar().getCarClassId(), advertisement.getCar().getFuelTypeId(), advertisement.getCar().getTransmissionId());
            AdvertisementDTO advertisementDTO = new AdvertisementDTO();
            advertisementDTO.setId(advertisement.getId());
            advertisementDTO.setStartDate(advertisement.getStartDate());
            advertisementDTO.setEndDate(advertisement.getEndDate());

            CarDTO carDTO = new CarDTO();
            carDTO.setId(advertisement.getCar().getId());
            carDTO.setCarModelId(codebookDTO.getCarModelId());
            carDTO.setCarClassId(codebookDTO.getCarClassId());
            carDTO.setFuelTypeId(codebookDTO.getFuelTypeId());
            carDTO.setTransmissionId(codebookDTO.getTransmissionId());
            carDTO.setAvailableChildSeats(advertisement.getCar().getAvailableChildSeats());
            carDTO.setPricePerDay(advertisement.getCar().getPricePerDay());
            carDTO.setPricePerKm(advertisement.getCar().getPricePerKm());
            carDTO.setKmage(advertisement.getCar().getKmage());
            carDTO.setLimitKmsPerDay(advertisement.getCar().getLimitKmsPerDay());
            carDTO.setWaiver(advertisement.getCar().isWaiver());
            carDTO.setLimitedKms(advertisement.getCar().isLimitedKms());
            carDTO.setLocationId(advertisement.getCar().getLocationId());
            carDTO.setOwnerId(advertisement.getCar().getOwnerId());

            advertisementDTO.setCarDTO(carDTO);
            advertisementDTOS.add(advertisementDTO);
        }
        return advertisementDTOS;

    }
}
