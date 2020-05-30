package xmlteam4.rentservice.service;

import xmlteam4.rentservice.model.RentRequest;

import java.util.List;

public interface RentService {

    List<RentRequest> getAll();

    RentRequest getOne(Long id);

    List<RentRequest> getPaidRentReqs();

    void createRentReq(RentRequest rentreq);
}
