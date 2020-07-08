package xmlteam4.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.DTO.*;
import xmlteam4.carservice.DTO.codebookh.CodebookDTOh;
import xmlteam4.carservice.Forms.CarSearchForm;
import xmlteam4.carservice.client.CodebookFeignClient;
import xmlteam4.carservice.model.*;
import xmlteam4.carservice.repository.CarCalendarRepository;
import xmlteam4.carservice.repository.CarRatingRepository;
import xmlteam4.carservice.repository.CarRepository;
import xmlteam4.carservice.repository.RentalRepository;

import java.util.*;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private CarCalendarRepository carCalendarRepository;
    @Autowired
    private CarRatingRepository carRatingRepository;
    @Autowired
    private CodebookFeignClient codebookFeignClient;

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
                    car.getCarClassId(), car.getFuelTypeId(), car.getTransmissionId());
            TempCarDTO tempCarDTO = new TempCarDTO();
            tempCarDTO.setId(car.getId());
            tempCarDTO.setCarBrandId(codebookDTO.getCarBrandDTO().getName());
            tempCarDTO.setCarModelId(codebookDTO.getCarModelDTO().getName());
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

    public ArrayList<Car> searchCars(CarSearchForm carSearchForm) {
        try {

            ArrayList<Rental> rentals = (ArrayList<Rental>) this.rentalRepository.findAll();
            ArrayList<Rental> freeRentals = (ArrayList<Rental>) this.rentalRepository.findFree(carSearchForm.getStartDate(), carSearchForm.getEndDate());
            ArrayList<Car> retVal = new ArrayList<>();
            ArrayList<CarCalendar> carCalendars = new ArrayList<>();
            for(Rental r : freeRentals){
                carCalendars.add(carCalendarRepository.getOne(r.getCarCalendarId()));
            }
            for(CarCalendar carCalendar : carCalendars){
                Car car =carRepository.getOne(carCalendar.getCarId());
                if(car.getLocationId().equals(carSearchForm.getLocationId()))
                    retVal.add(car);
            }
            return retVal;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Rental blockCar(Rental rental) {

        try {
            Rental rent = this.rentalRepository.save(rental);
            CarCalendar carCal = carCalendarRepository.getOne(rental.getCarCalendarId());
            carCal.getRentalIds().add(rent.getId());

            this.carCalendarRepository.save(carCal);

            return rent;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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


    public List<CarDTOPretty> prettyCars(List<Long> ids) {
        List<CarDTOPretty> cars = new ArrayList<>();
        CodebookDTOh codebookDTOh;
        try {
            codebookDTOh = codebookFeignClient.getCodebookDTOh();
        } catch (Exception e) {
            e.printStackTrace();
            return cars;
        }

        for (Long id : ids) {
            try {
                Car car = carRepository.getOne(id);
                cars.add(new CarDTOPretty(car, codebookDTOh));
            } catch (Exception e) {
                e.printStackTrace();
                return cars;
            }
        }

        return cars;
    }
}
