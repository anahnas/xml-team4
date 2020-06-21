package xmlteam4.carservice.soap;

import localhost._8081.car_service_schema.AdvertisementRequest;
import localhost._8081.car_service_schema.AdvertisementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xmlteam4.carservice.DTO.AdvertisementDTO;
import xmlteam4.carservice.DTO.CarDTO;
import xmlteam4.carservice.DTO.CodebookDTO;
import xmlteam4.carservice.DTO.NewAdvertisementDTO;
import xmlteam4.carservice.client.CodebookFeignClient;
import xmlteam4.carservice.model.Car;
import xmlteam4.carservice.repository.CarRepository;
import xmlteam4.carservice.service.AdvertisementService;

@Endpoint
public class AdvertisementEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8081/car-service-schema";

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private CodebookFeignClient codebookFeignClient;

    @Autowired
    private CarRepository carRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "advertisementRequest")
    @ResponsePayload
    public AdvertisementResponse newAdvertisement(@RequestPayload AdvertisementRequest adRequest) {
        System.out.println("ULAZI U SOAP KREIRANJE OGLASA");
        AdvertisementResponse adResponse = new AdvertisementResponse();
        NewAdvertisementDTO advertisementDTO = new NewAdvertisementDTO();

        //proba za ovaj DTO
        advertisementDTO.setAdvertiserId(adRequest.getAdvertiserId());

        CodebookDTO codebookDTO = this.codebookFeignClient.getCodebook(adRequest.getCar().getCarBrandId(),
                adRequest.getCar().getCarModelId(), adRequest.getCar().getCarClassId(), adRequest.getCar().getFuelTypeId(), adRequest.getCar().getTransmissionId());

        advertisementDTO.setCarBrandId(codebookDTO.getCarBrandId());
        advertisementDTO.setCarClassId(codebookDTO.getCarClassId());
        advertisementDTO.setCarModelId(codebookDTO.getCarModelId());
        advertisementDTO.setFuelTypeId(codebookDTO.getFuelTypeId());
        advertisementDTO.setTransmissionId(codebookDTO.getTransmissionId());
        advertisementDTO.setKmage(adRequest.getCar().getKmage());
        //fali stosta

        //GLUPA SAM VEC U newAdvertisement pravimo auto ups
        /*Car car = new Car();
        car.setAvailableChildSeats(adRequest.getCar().getAvailableChildSeats());
        car.setCarBrandId(adRequest.getCar().getCarBrandId());
        car.setCarClassId(adRequest.getCar().getCarClassId());
        car.setFuelTypeId(adRequest.getCar().getFuelTypeId());
        car.setTransmissionId(adRequest.getCar().getTransmissionId());
        car.setKmage(adRequest.getCar().getKmage());
        car.setLimitedKms(adRequest.getCar().isLimitedKms());
        car.setWaiver(adRequest.getCar().isWaiver());
        car.setLimitKmsPerDay(adRequest.getCar().getLimitedKmsPerDay());
        car.setPricePerDay(adRequest.getCar().getPricePerDay());
        car.setPricePerKm(adRequest.getCar().getPricePerKm());
*/
        CarDTO car = new CarDTO();
        if(adRequest.getCar().getId() != 0)
            car.setId(adRequest.getCar().getId());
        car.setAvailableChildSeats(adRequest.getCar().getAvailableChildSeats());
        car.setCarBrandId(adRequest.getCar().getCarBrandId());
        car.setCarClassId(adRequest.getCar().getCarClassId());
        car.setFuelTypeId(adRequest.getCar().getFuelTypeId());
        car.setTransmissionId(adRequest.getCar().getTransmissionId());
        car.setKmage(adRequest.getCar().getKmage());
        car.setLimitedKms(adRequest.getCar().isLimitedKms());
        car.setWaiver(adRequest.getCar().isWaiver());
        car.setLimitKmsPerDay(adRequest.getCar().getLimitedKmsPerDay());
        car.setPricePerDay(adRequest.getCar().getPricePerDay());
        car.setPricePerKm(adRequest.getCar().getPricePerKm());


        // Car car = this.carRepository.findTopByOrderByIdDesc();
        Long advertisementId = advertisementService.newAdvertisement(advertisementDTO);
        //samo ovo vracamo u response
        adResponse.setCarId(car.getId());
        adResponse.setAdvertisementId(advertisementId);

        return adResponse;
    }


}
