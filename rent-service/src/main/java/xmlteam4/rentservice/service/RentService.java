package xmlteam4.rentservice.service;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.rentservice.dto.CarDTOBasic;
import xmlteam4.rentservice.dto.RentReqDTO;
import xmlteam4.rentservice.dto.UserDTO;
import xmlteam4.rentservice.feign.CarFeign;
import xmlteam4.rentservice.feign.UserFeign;
import xmlteam4.rentservice.forms.RentForm;
import xmlteam4.rentservice.model.Bundle;
import xmlteam4.rentservice.model.Rent;
import xmlteam4.rentservice.model.RentStatus;
import xmlteam4.rentservice.repository.RentRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BundleService bundleService;

    @Autowired
    private CarFeign carFeign;

    @Autowired
    private UserFeign userFeign;


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

        for (RentForm rentForm : rentForms) {
            if (rentForm.getCarId() == null || rentForm.getClientId() == null ||
                    rentForm.getStartDate() == null || rentForm.getEndDate() == null)
                throw new Exception("One or more forms contain empty fields.");

            boolean exists = false;
            for (CarDTOBasic car : basicCars) {
                if (car.getId().equals(rentForm.getCarId())) {
                    exists = true;
                    break;
                }
            }

            if (!exists)
                throw new Exception("A car with id:'" + rentForm.getCarId() + "' does not exist.");
        }

        List<Rent> rents = new ArrayList<>();
        for (RentForm rentForm : rentForms) {
            rents.add(rentRepository.save(new Rent(rentForm)));
            Timer timer = new Timer("Timer");
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    cancelAfter24H();
                }
            };
            timer.schedule(timerTask, DateUtils.addHours(rentForm.getStartDate(), 24));
        }

        return rents;
    }

    public Bundle postBundle(List<RentForm> rentForms) throws Exception {
        if (rentForms.isEmpty())
            throw new Exception("No rent froms sent.");

        List<CarDTOBasic> basicCars = carFeign.basicCars();

        Long ownerId = null;
        for (RentForm rentForm : rentForms) {
            if (rentForm.getCarId() == null || rentForm.getClientId() == null ||
                    rentForm.getStartDate() == null || rentForm.getEndDate() == null)
                throw new Exception("One or more forms contain empty fields.");

            boolean exists = false;
            for (CarDTOBasic car : basicCars) {
                if (car.getId().equals(rentForm.getCarId())) {      //a car with the selected id exists
                    exists = true;
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
        for (RentForm rentForm : rentForms) {
            Rent rent = rentRepository.save(new Rent(rentForm, bundle.getId()));
            rentIds.add(rent.getId());
            Timer timer = new Timer("Timer");
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    cancelAfter24H();
                }
            };
            timer.schedule(timerTask, DateUtils.addHours(rentForm.getStartDate(), 24));
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


    public List<RentReqDTO> getClientRentRequests(Long id) {
        List<Rent> rentRequests = this.rentRepository.findByClientId(id);
        List<RentReqDTO> rentRequestDtos = new ArrayList<>();
        for (Rent request : rentRequests) {
            ENT2DTO(rentRequestDtos, request);
        }
        return rentRequestDtos;
    }

    private void ENT2DTO(List<RentReqDTO> rentRequestDtos, Rent request) {

        RentReqDTO rentRequestDto = new RentReqDTO();
        rentRequestDto.setId(request.getId());
        rentRequestDto.setStartDate(request.getStartDate());
        rentRequestDto.setEndDate(request.getEndDate());
        rentRequestDto.setStatus(request.getStatus());
        UserDTO client = this.userFeign.getUserById(request.getClientId());
        rentRequestDto.setCliendId(client.getId());
        rentRequestDtos.add(rentRequestDto);
    }

    public void cancelAfter24H() {
        Date rentDate = new Date();
        List<Rent> rents = rentRepository.findAll();
        for (Rent rent : rents) {
            if (rent.getEndDate().equals(rentDate) && rent.getStatus().equals(RentStatus.PENDING)) {
                rent.setStatus(RentStatus.CANCELED);
                rentRepository.save(rent);
                break;
            }
        }
    }



}
