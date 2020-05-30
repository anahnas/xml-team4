package xml.team4.CarService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.team4.CarService.model.Car;
import xml.team4.CarService.repository.CarRepository;
import xmlteam4.carservice.Forms.CarSearchForm;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

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

    public ArrayList<Car> searchCars(CarSearchForm car) {
        try {
            //return this.carRepository.findByCarModelIdAndAndCarClassIdAndAndFuelTypeIdAndAndTransmissionId(car.getCarModelId(), car.getCarClassId(), car.getFuelTypeId(), car.getTransmissionId());

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
