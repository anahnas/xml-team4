package xmlteam4.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.DTO.*;
import xmlteam4.carservice.DTO.codebookh.CodebookDTOh;
import xmlteam4.carservice.DTO.CarDTOBasic;
import xmlteam4.carservice.DTO.CodebookDTO;
import xmlteam4.carservice.DTO.TempCarDTO;
import xmlteam4.carservice.DTO.CarSearchDTO;
import xmlteam4.carservice.client.CodebookFeignClient;
import xmlteam4.carservice.client.RentFeignClient;
import xmlteam4.carservice.model.*;
import xmlteam4.carservice.repository.CarRatingRepository;
import xmlteam4.carservice.repository.CarRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarRatingRepository carRatingRepository;
    @Autowired
    private CodebookFeignClient codebookFeignClient;
    @Autowired
    private RentFeignClient rentFeignClient;
    @Autowired
    private AdvertisementService advertisementService;

    public ArrayList<Car> getAllCars(){
        return this.carRepository.findAll();
    }

    public ArrayList<TempCarDTO> getAllCarDTOs(){
        ArrayList<Car> cars = this.carRepository.findAll();
        ArrayList<TempCarDTO> carDTOs = new ArrayList<>();

        for(Car car : cars){
            TempCarDTO tempCarDTO = this.setCarDTO(car.getId());
            if(tempCarDTO != null)
                carDTOs.add(tempCarDTO);
        }
        return carDTOs;
    }

    public Car getCar(Long id){
        if(this.carRepository.findById(id).isPresent())
            return this.carRepository.findById(id).get();
        else
            return null;
    }

    public TempCarDTO getCarDTO(Long id){
        TempCarDTO tempCarDTO = setCarDTO(id);
        return tempCarDTO;
    }

    private TempCarDTO setCarDTO(Long id){
        if(this.carRepository.findById(id).isPresent()) {
            Car car = this.carRepository.findById(id).get();

            CodebookDTO codebookDTO = this.codebookFeignClient.getCodebook(car.getCarBrandId(), car.getCarModelId(),
                    car.getCarClassId(), car.getFuelTypeId(), car.getTransmissionId(), car.getLocationId());

            TempCarDTO tempCarDTO = new TempCarDTO();
            tempCarDTO.setId(car.getId());
            tempCarDTO.setOwnerId(car.getOwnerId());
            tempCarDTO.setCarBrandId(codebookDTO.getCarBrandDTO().getName());
            tempCarDTO.setCarModelId(codebookDTO.getCarModelDTO().getName());
            tempCarDTO.setLocationId(codebookDTO.getLocationDTO().getName());
            tempCarDTO.setCarClassId(codebookDTO.getCarClassDTO().getCarClass());
            tempCarDTO.setFuelTypeId(codebookDTO.getFuelTypeDTO().getType());
            tempCarDTO.setTransmissionId(codebookDTO.getTransmissionDTO().getType());
            tempCarDTO.setAvailableChildSeats(car.getAvailableChildSeats());
            tempCarDTO.setKmage(car.getKmage());
            tempCarDTO.setPricePerDay(car.getPricePerDay());
            tempCarDTO.setPricePerKm(car.getPricePerKm());
            tempCarDTO.setLimitedKms(car.isLimitedKms());
            tempCarDTO.setLimitKmsPerDay(car.getLimitKmsPerDay());
            tempCarDTO.setWaiver(car.isWaiver());
            tempCarDTO.setImagePath(car.getImagePath());
            Double rating = 0.0;
            List<CarRating> carRatings = this.carRatingRepository.findAllByCarId(car.getId());
            if (carRatings.size() != 0) {
                for (CarRating carRating: carRatings) {
                    rating += carRating.getRating();
                }
                rating = rating / carRatings.size();
                tempCarDTO.setRating(rating);
            }

            return tempCarDTO;
        }
        return null;
    }

    public Car addCar(Car car){
        return this.carRepository.save(car);
    }

    public Car editCar(Car carDTO){
        Car car = this.carRepository.getOne(carDTO.getId());

        car.setCarModelId(carDTO.getCarModelId());
        car.setFuelTypeId(carDTO.getFuelTypeId());
        car.setTransmissionId(carDTO.getTransmissionId());
        car.setCarClassId(carDTO.getCarClassId());
        car.setPricePerDay(carDTO.getPricePerDay());
        car.setPricePerKm(carDTO.getPricePerKm());
        car.setLimitedKms(carDTO.isLimitedKms());
        car.setLimitKmsPerDay(carDTO.getLimitKmsPerDay());
        car.setKmage(carDTO.getKmage());
        car.setWaiver(carDTO.isWaiver());
        car.setAvailableChildSeats(carDTO.getAvailableChildSeats());
        car.setOwnerId(carDTO.getOwnerId());
        car.setCarRatingIds(carDTO.getCarRatingIds());
        car.setPromotionIds(carDTO.getPromotionIds());

        return this.carRepository.save(car);
    }

    public boolean removeCar(Long id){
        try{
            this.carRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Long getOwner(Long id){
        Car car = this.carRepository.getOne(id);
        return car.getOwnerId();
    }

    public ArrayList<TempCarDTO> searchCars(CarSearchDTO carSearchDTO) {
        ArrayList<Car> toRemove = new ArrayList<>();
        ArrayList<Car> allCars = this.carRepository.findAll();

        try {
            System.out.println(carSearchDTO);

            if(carSearchDTO.getLocationId() != null){
                for(Car car: allCars) {
                    if (!car.getLocationId().equals(carSearchDTO.getLocationId()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }
            System.out.println("after location " + allCars.size());
            if(carSearchDTO.getCarModelId() != null){
                for(Car car: allCars) {
                    if (!car.getCarModelId().equals(carSearchDTO.getCarModelId()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }
            System.out.println("after getCarModelId " + allCars.size());

            if(carSearchDTO.getCarClassId() != null){
                for(Car car: allCars) {
                    if (!car.getCarClassId().equals(carSearchDTO.getCarClassId()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }
            System.out.println("after getCarClassId " + allCars.size());

            if(carSearchDTO.getFuelTypeId() != null){
                for(Car car: allCars) {
                    if (!car.getFuelTypeId().equals(carSearchDTO.getFuelTypeId()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }

            if(carSearchDTO.getTransmissionId() != null){
                for(Car car: allCars) {
                    if (!car.getTransmissionId().equals(carSearchDTO.getTransmissionId()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }

            if(carSearchDTO.getAvailableChildSeats() != 0){
                for(Car car: allCars) {
                    if (car.getAvailableChildSeats() != (carSearchDTO.getAvailableChildSeats()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }

            if(carSearchDTO.getKmage() != 0.0){
                for(Car car: allCars) {
                    if (!car.getKmage().equals(carSearchDTO.getKmage()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }

            if(carSearchDTO.isLimitedKms()){
                for(Car car: allCars) {
                    if (!car.isLimitedKms())
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            } else {
                for(Car car: allCars) {
                    if (car.isLimitedKms())
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }

            if(carSearchDTO.isWaiver()){
                for(Car car: allCars) {
                    if (!car.isWaiver())
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            } else {
                for(Car car: allCars) {
                    if (car.isWaiver())
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }
            System.out.println("after waiver " + allCars.size());

            if(carSearchDTO.getStartDate() != null && carSearchDTO.getEndDate() != null){
                if(!validStartDate(carSearchDTO.getStartDate()))
                    return null;

                RentDateDTO rentDateDTO = new RentDateDTO();

                rentDateDTO.setStartDate(carSearchDTO.getStartDate().toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDateTime());

                rentDateDTO.setEndDate(carSearchDTO.getEndDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());

                ArrayList<Long> carIds = new ArrayList<>();
                for(Car car: this.carRepository.findAll()){
                    carIds.add(car.getId());
                }
                rentDateDTO.setCarIds(carIds);
                ArrayList<Long> freeCarIds = this.rentFeignClient.getFree(rentDateDTO);

                for(Car car : allCars){
                    if(!freeCarIds.contains(car.getId()))
                        toRemove.add(car);
                }

                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }
            System.out.println("after date " + allCars.size());

            ArrayList<TempCarDTO> carDTOs = new ArrayList<>();

            for(Car car : allCars){
                TempCarDTO tempCarDTO = this.setCarDTO(car.getId());
                if(tempCarDTO != null)
                    carDTOs.add(tempCarDTO);
            }

            return carDTOs;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private boolean validStartDate(Date startDate){
        Date today = new Date();
        Instant instant = Instant.ofEpochMilli(today.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate todayLocal = localDateTime.toLocalDate();

        instant = Instant.ofEpochMilli(startDate.getTime());
        localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate startLocal = localDateTime.toLocalDate();
        long days = ChronoUnit.DAYS.between(todayLocal, startLocal);
        return days >= 2;

    }

    public List<CarDTOBasic> basicCars() {
        List<CarDTOBasic> basicCars = new ArrayList<>();
        try {
            List<Car> cars = getAllCars();
            for (Car car : cars) {
                basicCars.add(new CarDTOBasic(car));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return basicCars;
    }

    public List<CarDTOBasic> basicCarsByOwner(Long id) {
        List<CarDTOBasic> basicCars = new ArrayList<>();
        try {
            List<Car> cars = this.carRepository.findAllByOwnerId(id);
            for (Car car : cars) {
                basicCars.add(new CarDTOBasic(car));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return basicCars;
    }


    public CarDTOBasic basicCar(Long id) {
        CarDTOBasic basicCar = new CarDTOBasic();

        try {
            Car car = this.carRepository.getOne(id);
            basicCar = new CarDTOBasic(car);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return basicCar;
    }

    public byte[] getCarImage(Long id) throws IOException {
        String path = this.carRepository.getOne(id).getImagePath();

        if(path != null)
            return Files.readAllBytes(Paths.get(path));
        else
            return null;
    }

    public List<CarDTOPretty> prettyCars(String idString) {
        String[] ids = idString.split(",");
        List<CarDTOPretty> cars = new ArrayList<>();
        CodebookDTOh codebookDTOh;
        try {
            codebookDTOh = codebookFeignClient.getCodebookDTOh();
        } catch (Exception e) {
            e.printStackTrace();
            return cars;
        }

        for (String id : ids) {
            try {
                Car car = carRepository.getOne(Long.parseLong(id));
                cars.add(new CarDTOPretty(car, codebookDTOh));
            } catch (Exception e) {
                e.printStackTrace();
                return cars;
            }
        }

        return cars;
    }

    public void setImagePath(String path, String originalFileName){
        //getting the car id from uploaded image
        String advertisementId = originalFileName.split("-")[0];
        Advertisement advertisement = this.advertisementService.getAdvertisement(Long.valueOf(advertisementId));
        Car car = this.carRepository.findById(advertisement.getCar().getId()).get();
        car.setImagePath(path);
        carRepository.save(car);
    }
}
