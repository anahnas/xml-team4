package xmlteam4.rentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.rentservice.dto.CarDTOBasic;
import xmlteam4.rentservice.feign.CarFeign;
import xmlteam4.rentservice.forms.RentForm;
import xmlteam4.rentservice.forms.ReviewForm;
import xmlteam4.rentservice.model.Bundle;
import xmlteam4.rentservice.model.Rent;
import xmlteam4.rentservice.model.RentStatus;
import xmlteam4.rentservice.repository.RentRepository;

import java.util.*;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BundleService bundleService;

    @Autowired
    private CarFeign carFeign;


    public List<Rent> getAll() {
        return this.rentRepository.findAll();
    }

    public Rent getById(Long id) {
        return this.rentRepository.getOne(id);
    }

    public List<Rent> getPaidRents() {
        return this.rentRepository.getPaidRents();
    }

    public List<Rent> postRents(List<RentForm> rentForms) throws Exception {
        if (rentForms.isEmpty())
            throw new Exception("No rent froms sent.");

        List<CarDTOBasic> basicCars = carFeign.basicCars();

        List<Rent> rents = new ArrayList<>();
        for (RentForm rentForm : rentForms) {
            if (rentForm.getCarId() == null || rentForm.getClientId() == null ||
                    rentForm.getStartDate() == null || rentForm.getEndDate() == null)
                throw new Exception("One or more forms contain empty fields.");

            boolean exists = false;
            for (CarDTOBasic car : basicCars) {
                if (car.getId().equals(rentForm.getCarId())) {
                    exists = true;
                    rents.add(new Rent(rentForm, car));
                    break;
                }
            }

            if (!exists)
                throw new Exception("A car with id:'" + rentForm.getCarId() + "' does not exist.");
        }


        for (Rent rent : rents)
            rentRepository.save(rent);

        return rents;
    }

    public Bundle postBundle(List<RentForm> rentForms) throws Exception {
        if (rentForms.isEmpty())
            throw new Exception("No rent froms sent.");

        List<CarDTOBasic> basicCars = carFeign.basicCars();
        List<Rent> rents = new ArrayList<>();

        Long ownerId = null;
        for (RentForm rentForm : rentForms) {
            if (rentForm.getCarId() == null || rentForm.getClientId() == null ||
                    rentForm.getStartDate() == null || rentForm.getEndDate() == null)
                throw new Exception("One or more forms contain empty fields.");

            boolean exists = false;
            for (CarDTOBasic car : basicCars) {
                if (car.getId().equals(rentForm.getCarId())) {      //a car with the selected id exists
                    exists = true;
                    rents.add(new Rent(rentForm, car));
                    if (ownerId == null)
                        ownerId = car.getOwnerId();
                    else if (!ownerId.equals(car.getOwnerId()))
                        throw new Exception("Cars from different owners cannot be bundled.");
                    break;
                }
            }

            if (!exists)
                throw new Exception("A car with id:'" + rentForm.getCarId() + "' does not exist.");
        }

        Bundle bundle = bundleService.save(new Bundle());
        Set<Long> rentIds = new HashSet<>();
        for (Rent rent : rents) {
            rent.setBundleId(bundle.getId());
            rentRepository.save(rent);
            rentIds.add(rent.getId());
        }
        bundle.setRentIds(rentIds);
        bundle = bundleService.save(bundle);

        return bundle;
    }

    public void acceptRentRequest(Long id) {
        Rent rentRequest = this.rentRepository.findById(id).get();
        rentRequest.setStatus(RentStatus.PAID);
        this.rentRepository.save(rentRequest);
    }

    public void cancelRentRequest(Long id) {
        Rent rentRequest = this.rentRepository.findById(id).get();
        rentRequest.setStatus(RentStatus.CANCELED);
        this.rentRepository.save(rentRequest);
    }


    public Rent updateAfterReview(ReviewForm reviewForm) throws Exception {
        if (reviewForm.getPenaltyKms() <= 0)
            throw new Exception("Penalty KMs must be grater than zero.");

        Optional<Rent> optionalRent = this.rentRepository.findById(reviewForm.getRentId());
        Rent rent;
        if (optionalRent.isPresent())
            rent = optionalRent.get();
        else
            throw new Exception("Rent with id: " + reviewForm.getRentId() + " does not exist.");

        CarDTOBasic car;
        try {
            car = this.carFeign.basicCar(rent.getCarId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error getting car info.");
        }

        rent.setPricePenalty(reviewForm.getPenaltyKms() * car.getPricePerKm());
        rent.setStatus(RentStatus.EXCEEDED);
        return this.rentRepository.save(rent);
    }

    public Rent pay(Long id) throws Exception {
        Optional<Rent> optionalRent = this.rentRepository.findById(id);
        Rent rent;
        if (optionalRent.isPresent())
            rent = optionalRent.get();
        else
            throw new Exception("Rent with id: " + id + " does not exist.");

        if (!rent.getStatus().equals(RentStatus.EXCEEDED))
            throw new Exception("There is nothing to pay");

        rent.setStatus(RentStatus.PAID);
        return this.rentRepository.save(rent);
    }
}
