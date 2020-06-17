package xmlteam4.rentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.rentservice.model.Bundle;
import xmlteam4.rentservice.repository.BundleRepository;

@Service
public class BundleService {
    @Autowired
    private BundleRepository bundleRepository;

    public Bundle save(Bundle bundle) { return bundleRepository.save(bundle); }

    public void delete(Bundle bundle) { bundleRepository.delete(bundle); }
}
