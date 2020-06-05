package xmlteam4.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.DTO.CarDTO;
import xmlteam4.carservice.DTO.CarDTOBasic;
import xmlteam4.carservice.DTO.CodebookDTO;
import xmlteam4.carservice.Forms.CarSearchForm;
import xmlteam4.carservice.client.CodebookFeignClient;
import xmlteam4.carservice.model.Car;
import xmlteam4.carservice.model.CarCalendar;
import xmlteam4.carservice.model.Rental;
import xmlteam4.carservice.repository.CarCalendarRepository;
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

    public ArrayList<Car> getAllCars(){
        return this.carRepository.findAll();
    }

    public Car getCar(Long id){
        if(this.carRepository.findById(id).isPresent())
            return this.carRepository.findById(id).get();
        else
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

}
