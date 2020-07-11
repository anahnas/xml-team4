package xmlteam4.rentservice.service;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.rentservice.dto.CarDTOBasic;
import xmlteam4.rentservice.dto.RentReqDTO;
import xmlteam4.rentservice.dto.UserDTO;
import xmlteam4.rentservice.dto.RentDateDTO;
import xmlteam4.rentservice.feign.CarFeign;
import xmlteam4.rentservice.feign.UserFeign;
import xmlteam4.rentservice.forms.RentForm;
import xmlteam4.rentservice.forms.ReviewForm;
import xmlteam4.rentservice.model.Bundle;
import xmlteam4.rentservice.model.Rent;
import xmlteam4.rentservice.model.RentStatus;
import xmlteam4.rentservice.repository.RentRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import javax.transaction.Transactional;
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

    public List<Rent> getAllForUser(Long id) {
        List<CarDTOBasic> cars = this.carFeign.getAllCarsByOwner(id);
        List<Rent> rents = new ArrayList<>();
        for(CarDTOBasic car: cars){
            rents.addAll(this.rentRepository.findAllByCarId(car.getId()));
        }
        return rents;
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

            //  List<Rent> rents = new ArrayList<>();
            for (Rent rent : rents) {
               // rents.add(rentRepository.save(new Rent(rent)));
                rentRepository.save(rent);
                Timer timer = new Timer("Timer");
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        cancelAfter24H();
                    }
                };
                timer.schedule(timerTask, DateUtils.addHours(rent.getStartDate2(), 24));

        }



        return rents;
    }


    public List<Long> getFreeCarIds(RentDateDTO rentDateDTO) {
        ArrayList<Long> freeCarIds = new ArrayList<>();

        for(Long id: rentDateDTO.getCarIds()){
            if(carIsFree(rentDateDTO, id)){
                freeCarIds.add(id);
            }
        }
        return freeCarIds;
    }

    private boolean carIsFree(RentDateDTO rentDateDTO, Long carId) {

        List<Rent> rents = this.rentRepository.findAllByCarId(carId);

        LocalDateTime today = LocalDateTime.now();

        for (Rent r : rents) {
            if(((r.getStartDate().isBefore(today) || (r.getStartDate().equals(today))) && r.getEndDate().isAfter(today)) && !(rentDateDTO.getStartDate().isAfter(r.getEndDate())))
            {
                System.out.println("Automobil je vec izdat nekom i placen!");
                return false;
            } else if (rentDateDTO.getStartDate().isBefore(today) || rentDateDTO.getEndDate().isBefore(today)) {
                System.out.println("Ne mozete iznajmiti automobil u proslosti!");
                return false;

            } else {

                if ( r.getStartDate().isAfter(rentDateDTO.getStartDate())  && r.getEndDate().isBefore(rentDateDTO.getEndDate()) ) {
                    // Moze da se zakazati (unutar termina).
                    return false;
                } else if ((r.getStartDate().isBefore(rentDateDTO.getStartDate())) && (r.getEndDate().isBefore(rentDateDTO.getEndDate()) && r.getEndDate().isAfter(rentDateDTO.getStartDate()))) {
                    return false;
                } else if (  (r.getStartDate().isBefore(rentDateDTO.getEndDate()) && r.getStartDate().isAfter(rentDateDTO.getStartDate())) && r.getEndDate().isAfter(rentDateDTO.getEndDate())) {
                    return false;
                } else if (r.getStartDate().equals(rentDateDTO.getStartDate()) && (r.getEndDate().isBefore(rentDateDTO.getEndDate()) || r.getEndDate().isAfter(rentDateDTO.getEndDate()))) {
                    // Moze da se zakazati (unutar termina).
                    return false;
                } else if ( r.getStartDate().isBefore(rentDateDTO.getStartDate())  && r.getEndDate().isAfter(rentDateDTO.getEndDate()) ) {
                    // Moze da se zakazati (unutar termina).
                    return false;
                } else if ((r.getStartDate().isBefore(rentDateDTO.getStartDate()) || r.getStartDate().isAfter(rentDateDTO.getStartDate())) && r.getEndDate().equals(rentDateDTO.getEndDate())) {
                    // Moze da se zakazati (unutar termina).
                    return false;
                } else {
                    continue;
                }
            }
        }
        return true;

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
            Timer timer = new Timer("Timer");
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    cancelAfter24H();
                }
            };
            timer.schedule(timerTask, DateUtils.addHours(rent.getStartDate2(), 24));
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

    @Transactional
    public Rent blockCar(Rent rent) {

        try {

            List<Rent> rents = this.rentRepository.findAllByCarId(rent.getCarId());
            ArrayList<Long> deleteListId = new ArrayList<>();
            LocalDateTime today = LocalDateTime.now();

            for (Rent r : rents) {
                if(((r.getStartDate().isBefore(today) || (r.getStartDate().equals(today))) && r.getEndDate().isAfter(today)) &&
                        !(rent.getStartDate().isAfter(r.getEndDate()))) {
                    System.out.println("Automobil je vec izdat nekom i placen!");
                    return null;
                } else if (rent.getStartDate().isBefore(today) || rent.getEndDate().isBefore(today)) {
                    System.out.println("Ne mozete iznajmiti automobil u proslosti!");
                    return null;
                } else {

                    if ( r.getStartDate().isAfter(rent.getStartDate())  && r.getEndDate().isBefore(rent.getEndDate()) ) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  1 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rent.getStartDate() + " ,  " + rent.getEndDate());
                        continue;
                    } else if ((r.getStartDate().isBefore(rent.getStartDate())) && (r.getEndDate().isBefore(rent.getEndDate()) && r.getEndDate().isAfter(rent.getEndDate()))) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  2 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rent.getStartDate() + " ,  " + rent.getEndDate());
                        continue;
                    } else if (  (r.getStartDate().isBefore(rent.getEndDate()) && r.getStartDate().isAfter(rent.getStartDate())) && r.getEndDate().isAfter(rent.getEndDate())) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  3 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rent.getStartDate() + " ,  " + rent.getEndDate());
                        continue;
                    } else if (r.getStartDate().equals(rent.getStartDate()) && (r.getEndDate().isBefore(rent.getEndDate()) || r.getEndDate().isAfter(rent.getEndDate()))) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  4 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rent.getStartDate() + " ,  " + rent.getEndDate());
                        continue;
                    } else if ( r.getStartDate().isBefore(rent.getStartDate())  && r.getEndDate().isAfter(rent.getEndDate()) ) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  5 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rent.getStartDate() + " ,  " + rent.getEndDate());
                        continue;
                    } else if ((r.getStartDate().isBefore(rent.getStartDate()) || r.getStartDate().isAfter(rent.getStartDate())) && r.getEndDate().equals(rent.getEndDate())) {
                        // Moze da se zakazati (unutar termina).
                        deleteListId.add(r.getId());
                        System.out.println("Moze  6 !");
                        System.out.println("Stari  " + r.getStartDate() + " ,  " + r.getEndDate() + "\n Novi  " + rent.getStartDate() + " ,  " + rent.getEndDate());
                        continue;
                    } else {
                        System.out.println(" Proslo 7 !");
                        continue;
                    }
                }
            }
            // Brisanje liste rentala
            this.rentRepository.deleteRentsWithIds(deleteListId);





            // Cuvanje novog rentala i njegovo dodavanje
            rent.setStatus(RentStatus.RESERVED);
            return this.rentRepository.save(rent);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
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
        rentRequestDto.setStartDate(Date.from(request.getStartDate().atZone(ZoneId.systemDefault()).toInstant()));
        rentRequestDto.setEndDate(Date.from(request.getEndDate().atZone(ZoneId.systemDefault()).toInstant()));
        rentRequestDto.setStatus(request.getStatus());
        UserDTO client = this.userFeign.getUser(request.getClientId());
        rentRequestDto.setClientId(client.getId());
        rentRequestDto.setClient(client.getUsername());
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
