package xmlteam4.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.carservice.DTO.CarDTOBasic;
import xmlteam4.carservice.DTO.CodebookDTO;
import xmlteam4.carservice.DTO.TempCarDTO;
import xmlteam4.carservice.DTO.CarSearchDTO;
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
                    car.getCarClassId(), car.getFuelTypeId(), car.getTransmissionId(), car.getLocationId());
            TempCarDTO tempCarDTO = new TempCarDTO();
            tempCarDTO.setId(car.getId());
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

            if(carSearchDTO.getCarModelId() != null){
                for(Car car: allCars) {
                    if (!car.getCarModelId().equals(carSearchDTO.getCarModelId()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }

            if(carSearchDTO.getCarClassId() != null){
                for(Car car: allCars) {
                    if (!car.getCarClassId().equals(carSearchDTO.getCarClassId()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }

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

            if(carSearchDTO.getStartDate() != null && carSearchDTO.getEndDate() != null){
                ArrayList<Rental> rentals = (ArrayList<Rental>) this.rentalRepository.findAll();
                ArrayList<Rental> freeRentals = (ArrayList<Rental>) this.rentalRepository.findFree(carSearchDTO.getStartDate(), carSearchDTO.getEndDate());
                ArrayList<CarCalendar> carCalendars = new ArrayList<>();
                for(Rental r : freeRentals){
                    carCalendars.add(carCalendarRepository.getOne(r.getCarCalendarId()));
                }
                ArrayList<Car> freeCars = new ArrayList<>();
                for(CarCalendar carCalendar : carCalendars){
                    Car car = carRepository.getOne(carCalendar.getCarId());
                    freeCars.add(car);
                }
                for(Car car : allCars){
                    if(!freeCars.contains(car.getId()))
                        toRemove.add(car);
                }
                allCars.removeAll(toRemove);
                toRemove = new ArrayList<>();
            }

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


}
