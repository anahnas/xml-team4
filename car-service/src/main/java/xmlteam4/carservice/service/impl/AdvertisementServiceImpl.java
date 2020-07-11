package xmlteam4.carservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xmlteam4.carservice.DTO.*;
import xmlteam4.carservice.DTO.codebookh.CarModelDTOh;
import xmlteam4.carservice.DTO.codebookh.CodebookDTOh;
import xmlteam4.carservice.client.CodebookFeignClient;
import xmlteam4.carservice.client.UserFeignClient;
import xmlteam4.carservice.model.Advertisement;
import xmlteam4.carservice.model.Car;
import xmlteam4.carservice.model.Image;
import xmlteam4.carservice.repository.AdvertisementRepository;
import xmlteam4.carservice.repository.ImageRepository;
import xmlteam4.carservice.service.AdvertisementService;
import xmlteam4.carservice.service.CarService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CodebookFeignClient codebookFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private CarService carService;

    @Autowired
    private ServletContext servletContext;

    @Override
    public List<AdvertisementDTO> getAll() {
        List<AdvertisementDTO> advertisementDTOS = new ArrayList<>();
        List<Advertisement> advertisements = this.advertisementRepository.findAll();
        return getAdDTOS(advertisementDTOS, advertisements);
    }


    @Override
    public Long newAdvertisement(NewAdvertisementDTO newAdvertisementDTO) {
        Car car = new Car();

        car.setCarModelId(newAdvertisementDTO.getCarModelId());

        CodebookDTOh codebookDTOh = this.codebookFeignClient.getCodebookDTOh();
        List<CarModelDTOh> carModelDTOhs = codebookDTOh.getCarModelDTOhs();
        for(CarModelDTOh c : carModelDTOhs){
            if(c.getId().equals(newAdvertisementDTO.getCarModelId())){
                car.setCarBrandId(c.getBrandId());
                car.setCarClassId(c.getClassId());
            }
        }

        car.setFuelTypeId(newAdvertisementDTO.getFuelTypeId());
        car.setTransmissionId(newAdvertisementDTO.getTransmissionId());
        car.setLocationId(newAdvertisementDTO.getLocationId());
        car.setKmage(newAdvertisementDTO.getKmage());
        car.setWaiver(newAdvertisementDTO.isWaiver());
        car.setAvailableChildSeats(newAdvertisementDTO.getAvailableChildSeats());
        car.setPricePerDay(newAdvertisementDTO.getPricePerDay());
        car.setPricePerKm(newAdvertisementDTO.getPricePerKm());
        car.setLimitKmsPerDay(newAdvertisementDTO.getLimitKmsPerDay());
        car.setLimitedKms(newAdvertisementDTO.isLimitedKms());
        car.setOwnerId(newAdvertisementDTO.getAdvertiserId());
        this.carService.addCar(car);

        Advertisement advertisement = new Advertisement();
        advertisement.setCar(car);
        advertisement.setAdvertiserId(newAdvertisementDTO.getAdvertiserId());
        advertisement.setStartDate(newAdvertisementDTO.getStartDate());
        advertisement.setEndDate(newAdvertisementDTO.getEndDate());
        advertisement.setAdvertiserId(newAdvertisementDTO.getAdvertiserId());
        this.advertisementRepository.save(advertisement);
        this.advertisementRepository.flush();

        return advertisement.getId();

    }

    @Override
    public List<Advertisement> findAdvertisersAds(Long advertiserId) {
        return this.advertisementRepository.findAdsByAdvertiserId(advertiserId);
    }

    @Override
    public int counter(Long id) {
        return this.advertisementRepository.counter(id);
    }

    private List<AdvertisementDTO> getAdDTOS(List<AdvertisementDTO> advertisementDTOS, List<Advertisement> advertisements){
        for(Advertisement advertisement : advertisements) {
            UserDTO userDTO = this.userFeignClient.getUser(advertisement.getAdvertiserId());

            CodebookDTO codebookDTO = this.codebookFeignClient.getCodebook(advertisement.getCar().getCarBrandId(), advertisement.getCar().getCarModelId(), advertisement.getCar().getCarClassId(), advertisement.getCar().getFuelTypeId(), advertisement.getCar().getTransmissionId());
            AdvertisementDTO advertisementDTO = new AdvertisementDTO();
            advertisementDTO.setId(advertisement.getId());
            advertisementDTO.setStartDate(advertisement.getStartDate());
            advertisementDTO.setEndDate(advertisement.getEndDate());

            CarDTO carDTO = new CarDTO();
            carDTO.setCarModelDTO(codebookDTO.getCarModelDTO());
            carDTO.setCarBrandDTO(codebookDTO.getCarBrandDTO());
            carDTO.setCarClassDTO(codebookDTO.getCarClassDTO());
            carDTO.setFuelTypeDTO(codebookDTO.getFuelTypeDTO());
            carDTO.setTransmissionDTO(codebookDTO.getTransmissionDTO());
            carDTO.setId(advertisement.getCar().getId());
            carDTO.setCarBrandId(codebookDTO.getCarBrandId());
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
            //carDTO.setImageId(advertisement.getCar().getImageId());

            advertisementDTO.setCarDTO(carDTO);
            advertisementDTO.setUserDTO(userDTO);
            advertisementDTOS.add(advertisementDTO);
        }
        return advertisementDTOS;

    }

    @Override
    public void uploadImage(MultipartFile image) throws IOException {
        String pathStr= servletContext.getRealPath(image.getOriginalFilename());
        System.out.println(pathStr);
        byte[] bytes = image.getBytes();
        Files.write(Paths.get(pathStr), bytes);
        this.carService.setImagePath(pathStr, Objects.requireNonNull(image.getOriginalFilename()));
    }

    public Advertisement getAdvertisement(Long id){
        return this.advertisementRepository.getOne(id);
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
