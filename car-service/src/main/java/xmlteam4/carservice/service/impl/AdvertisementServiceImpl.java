package xmlteam4.carservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.DTO.*;
import xmlteam4.carservice.client.CodebookFeignClient;
import xmlteam4.carservice.model.Advertisement;
import xmlteam4.carservice.model.Car;
import xmlteam4.carservice.model.Image;
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

    public Image addImage(ImageDTO imageDTO){
        Image image = new Image();
        image.setPath(imageDTO.getPath());

        return image;
    }

    @Override
    public Long newAdvertisement(NewAdvertisementDTO newAdvertisementDTO) {
        Car car = new Car();
        car.setCarBrandId(newAdvertisementDTO.getCarBrandId());
        car.setCarModelId(newAdvertisementDTO.getCarModelId());
        car.setCarClassId(newAdvertisementDTO.getCarClassId());
        car.setFuelTypeId(newAdvertisementDTO.getFuelTypeId());
        car.setTransmissionId(newAdvertisementDTO.getTransmissionId());
        car.setKmage(newAdvertisementDTO.getKmage());
        car.setWaiver(newAdvertisementDTO.isWaiver());
        car.setAvailableChildSeats(newAdvertisementDTO.getAvailableChildSeats());
        car.setPricePerDay(newAdvertisementDTO.getPricePerDay());
        car.setPricePerKm(newAdvertisementDTO.getPricePerKm());
        car.setLimitKmsPerDay(newAdvertisementDTO.getLimitKmsPerDay());
        car.setLimitedKms(newAdvertisementDTO.isLimitedKms());
        this.carService.addCar(car);

        Advertisement advertisement = new Advertisement();
        advertisement.setCar(car);
        advertisement.setAdvertiserId(newAdvertisementDTO.getAdvertiserId());
        advertisement.setStartDate(newAdvertisementDTO.getStartDate());
        advertisement.setEndDate(newAdvertisementDTO.getEndDate());
        this.advertisementRepository.save(advertisement);
        this.advertisementRepository.flush();

        return advertisement.getId();

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

    /*private Advertisement DTO2Entity(AdvertisementDTO advertisementDTO) {

        Advertisement advertisement = new Advertisement();

        //NULL
        CodebookDTO codebookDTO = this.codebookFeignClient.getCodebook(advertisement.getCar().getCarBrandId(), advertisement.getCar().getCarModelId(), advertisement.getCar().getCarClassId(), advertisement.getCar().getFuelTypeId(), advertisement.getCar().getTransmissionId());
        advertisement.setId(advertisementDTO.getId());
        advertisement.setStartDate(advertisementDTO.getStartDate());
        advertisement.setEndDate(advertisementDTO.getEndDate());

        Car car = new Car();
        car.setId(advertisementDTO.getCarDTO().getId());
        car.setCarModelId(codebookDTO.getCarModelId());
        car.setCarClassId(codebookDTO.getCarClassId());
        car.setFuelTypeId(codebookDTO.getFuelTypeId());
        car.setTransmissionId(codebookDTO.getTransmissionId());
        car.setAvailableChildSeats(advertisementDTO.getCarDTO().getAvailableChildSeats());
        car.setPricePerDay(advertisementDTO.getCarDTO().getPricePerDay());
        car.setPricePerKm(advertisementDTO.getCarDTO().getPricePerKm());
        car.setKmage(advertisementDTO.getCarDTO().getKmage());
        car.setLimitKmsPerDay(advertisementDTO.getCarDTO().getLimitKmsPerDay());
        car.setWaiver(advertisementDTO.getCarDTO().isWaiver());
        car.setLimitedKms(advertisementDTO.getCarDTO().isLimitedKms());
        car.setLocationId(advertisementDTO.getCarDTO().getLocationId());
        car.setOwnerId(advertisementDTO.getCarDTO().getOwnerId());

        advertisement.setCar(car);
        return advertisement;
    }*/
}
