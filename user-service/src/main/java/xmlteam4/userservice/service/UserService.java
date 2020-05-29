package xmlteam4.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.userservice.model.Admin;
import xmlteam4.userservice.model.BasicUser;
import xmlteam4.userservice.model.RoleTypes;
import xmlteam4.userservice.model.User;
import xmlteam4.userservice.repository.AdminRepository;
import xmlteam4.userservice.repository.BasicUserRepository;
import xmlteam4.userservice.repository.UserRepository;

import java.util.List;

import static xmlteam4.userservice.model.RoleTypes.BASIC_USER;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BasicUserRepository basicUserRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(User user) {
        User u = this.userRepository.findByUsername(user.getUsername());
        if(u != null) {
            if(u.getPassword().equals(user.getPassword())) {
                return u;
            }
        }

        return null;
    }

    public BasicUser register(BasicUser user) {
        BasicUser u = this.basicUserRepository.findByUsername(user.getUsername());
        if(u == null) {
            user.setRoleType(BASIC_USER);
            u = this.basicUserRepository.save(user);
            return u;
        }

        return null;
    }

    public void changeRoleType(User user, String roleType) {
        // RoleTypes.valueOf(roleType);
         System.out.println(RoleTypes.valueOf(roleType));
        if(roleType == "ADMIN"){
            Admin admin = new Admin();
            admin.setRoleType(RoleTypes.valueOf(roleType));
            admin.setUsername(user.getUsername());
            admin.setPassword(user.getPassword());
            userRepository.deleteById(user.getId());
            this.adminRepository.save(admin);
        }

    }

    public User addNewUser(User u) {
        System.out.println(u.getUsername() + " noviii");
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.getUsername().equals(u.getUsername())) {
                System.out.println("Ne moze!");
                return null;
            }
        }

        u.setRoleType(RoleTypes.BASIC_USER);
        this.userRepository.save(u);

        return u;
    }
}
