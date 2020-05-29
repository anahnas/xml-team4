package xmlteam4.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.userservice.model.BasicUser;

import java.util.ArrayList;

public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {

    ArrayList<BasicUser> findAll();
    BasicUser findByUsername(String username);
    void deleteById(Long id);
    BasicUser save(BasicUser user);


}