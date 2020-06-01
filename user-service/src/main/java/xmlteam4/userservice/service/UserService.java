package xmlteam4.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import xmlteam4.userservice.model.Admin;
import xmlteam4.userservice.model.BasicUser;
import xmlteam4.userservice.model.RoleTypes;
import xmlteam4.userservice.model.User;
import xmlteam4.userservice.repository.AdminRepository;
import xmlteam4.userservice.repository.BasicUserRepository;
import xmlteam4.userservice.repository.UserRepository;
import xmlteam4.userservice.service.MessageService;

import javax.management.relation.Role;
import java.util.ArrayList;
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
    @Autowired
    private MessageService messageService;

    /////////////////////  User  //////////////////////

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

    ///////////////////  Admin  //////////////////////

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

    public User blockUser(User u) {

        System.out.println("Korisnik: " + u.getUsername() + " treba biti blokiran!");

        User user = this.userRepository.getOne(u.getId());
        if(user.isBlocked() == true) {
            return null;
        }

        user.setBlocked(true);

        this.userRepository.save(user);

        return user;
    }

    public User activateUser(User u) {

        System.out.println("Korisnik " + u.getUsername() + " treba biti aktiviran!");

        User user = this.userRepository.getOne(u.getId());
        if(user.isBlocked() == false) {
            return null;
        }

        user.setBlocked(false);

        this.userRepository.save(user);

        return user;
    }

    public User deleteUser(User u) {

        System.out.println("Korisnik " + u.getUsername() + " treba biti obrisan!");

        try {
            this.userRepository.deleteById(u.getId());
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
