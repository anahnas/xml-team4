package xmlteam4.rentservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.rentservice.model.RentRequest;
import xmlteam4.rentservice.model.RentStatus;
import xmlteam4.rentservice.repository.RentRepository;
import xmlteam4.rentservice.service.RentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {


    @Autowired
    private RentRepository rentRepository;

    @Override
    public List<RentRequest> getAll() {
        return this.rentRepository.findAll();
    }

    @Override
    public RentRequest getOne(Long id) {
        return this.rentRepository.getOne(id);
    }

    @Override
    public List<RentRequest> getPaidRentReqs() {
        return this.rentRepository.getPaidRentReqs();
    }

    @Override
    public void createRentReq(RentRequest rentreq) {

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime fromDateAndTime = LocalDateTime.of(currentDate, currentTime);
        rentreq.setStartDate(fromDateAndTime);

        LocalDateTime aDateTime = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
        rentreq.setEndDate(aDateTime);

        rentreq.setRentStatus(RentStatus.PENDING);

        // rentreq.setClientId();

        this.rentRepository.save(rentreq);

    }

}
