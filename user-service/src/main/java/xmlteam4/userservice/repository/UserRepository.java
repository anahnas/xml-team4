package xmlteam4.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlteam4.userservice.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    ArrayList<User> findAll();
    User findByUsername(String username);
    void deleteById(Long id);
    User save(User user);

    @Query("SELECT u FROM User u WHERE u.roleType = 'BASIC_USER'")
    List<User> findBasicUsers();


}
