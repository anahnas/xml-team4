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
        System.out.println("AD REQ" + adRequest.getCar().getId() + ", " + adRequest.getCar().getCarModelId() +
                "," + adRequest.getCar().getImagePath() + "," + adRequest.getCar().getOwnerId() + ", " + adRequest.getAdvertiserId()
        + ", " + adRequest.getCar().getKmage());
        AdvertisementResponse adResponse = new AdvertisementResponse();
        System.out.println("prvi");

        NewAdvertisementDTO advertisementDTO = new NewAdvertisementDTO();

        //proba za ovaj DTO
        advertisementDTO.setAdvertiserId(adRequest.getAdvertiserId());
        System.out.println("Id lika koji je postavio oglas setovano u AdClient" + adRequest.getAdvertiserId());


       /* CodebookDTO codebookDTO = this.codebookFeignClient.getCodebook(adRequest.getCar().getCarBrandId(),
                adRequest.getCar().getCarModelId(), adRequest.getCar().getCarClassId(), adRequest.getCar().getFuelTypeId(), adRequest.getCar().getTransmissionId());
*/
        advertisementDTO.setCarModelId(adRequest.getCar().getCarModelId());
        advertisementDTO.setFuelTypeId(adRequest.getCar().getFuelTypeId());
        advertisementDTO.setTransmissionId(adRequest.getCar().getTransmissionId());
        advertisementDTO.setKmage(adRequest.getCar().getKmage());
        advertisementDTO.setAvailableChildSeats(adRequest.getCar().getAvailableChildSeats());
        advertisementDTO.setLimitedKms(adRequest.getCar().isLimitedKms());
        advertisementDTO.setLimitKmsPerDay(adRequest.getCar().getLimitedKmsPerDay());
        advertisementDTO.setPricePerDay(adRequest.getCar().getPricePerDay());
        advertisementDTO.setPricePerKm(adRequest.getCar().getPricePerKm());
        advertisementDTO.setWaiver(adRequest.getCar().isWaiver());
        advertisementDTO.setRoleType(" ");
        advertisementDTO.setImagePath(adRequest.getCar().getImagePath());


        //fali stosta

        //GLUPA SAM VEC U newAdvertisement pravimo auto ups
       /* Car car = new Car();
        car.setAvailableChildSeats(adRequest.getCar().getAvailableChildSeats());
        car.setCarModelId(adRequest.getCar().getCarModelId());
        car.setFuelTypeId(adRequest.getCar().getFuelTypeId());
        car.setTransmissionId(adRequest.getCar().getTransmissionId());
        car.setKmage(adRequest.getCar().getKmage());
        car.setLimitedKms(adRequest.getCar().isLimitedKms());
        car.setWaiver(adRequest.getCar().isWaiver());
        car.setLimitKmsPerDay(adRequest.getCar().getLimitedKmsPerDay());
        car.setPricePerDay(adRequest.getCar().getPricePerDay());
        car.setPricePerKm(adRequest.getCar().getPricePerKm());

*/
        Car car = this.carRepository.findTopByOrderByIdDesc();
        Long advertisementId = advertisementService.newAdvertisement(advertisementDTO);
        System.out.println("Koji je ADID? : "+ advertisementId);

        //samo ovo vracamo u response
        adResponse.setCarId(car.getId());
        System.out.println("Koji je CAR ID? : "+ car.getId());

        adResponse.setAdvertisementId(advertisementId);

        return adResponse;
    }


}
