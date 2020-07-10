package xmlteam4.rentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.rentservice.dto.CarDTOBasic;
import xmlteam4.rentservice.dto.RentDateDTO;
import xmlteam4.rentservice.feign.CarFeign;
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
        for (RentForm rentForm : rentForms)
            rents.add(rentRepository.save(new Rent(rentForm)));

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

    public boolean carIsFree(RentDateDTO rentDateDTO, Long carId) {

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
            return this.rentRepository.save(rent);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
