package xmlteam4.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.userservice.model.BasicUser;
import xmlteam4.userservice.model.DTO.UserDTO;
import xmlteam4.userservice.model.Message;
import xmlteam4.userservice.model.User;
import xmlteam4.userservice.service.MessageService;
import xmlteam4.userservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /////////////////////////////  User  /////////////////////////////

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User u) {
        User user = userService.login(u);
        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody BasicUser u) {
        BasicUser user = userService.register(u);
        if(user == null){
            return new ResponseEntity<>("Username taken.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/changeRole/{roleType}", method = RequestMethod.POST)
    public ResponseEntity<?> changeRole(@RequestBody User u, @PathVariable String roleType) {
        userService.changeRoleType(u, roleType);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/findBasic")
    public ResponseEntity<List<User>> getBasicUsers() {

        List<User> retVal = userService.findBasicUsers();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/findOne/{id}")
        public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User retVal = userService.findById(id);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public UserDTO getUser(@PathVariable("id") Long id) {
        UserDTO userDTO = new UserDTO();
        User user = this.userService.findById(id);
        userDTO.setId(id);
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setType(user.getRoleType().toString());
        return userDTO;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> retVal = userService.getAll();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    /////////////////////////////  ADMIN   /////////////////////////////

    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    public ResponseEntity<?> changeRole(@RequestBody User u) {
        User user = userService.addNewUser(u);
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);

        return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/blockUser", method = RequestMethod.POST)
    public ResponseEntity<?> blockUser(@RequestBody User u) {
        User user = userService.blockUser(u);
        if(user == null)
            return new ResponseEntity<>("User can't be blocked.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>( user, HttpStatus.OK);
    }

    @RequestMapping(value = "/activateUser", method = RequestMethod.POST)
    public ResponseEntity<?> activateUser(@RequestBody User u) {
        User user = userService.activateUser(u);
        if(user == null)
            return new ResponseEntity<>( "User can't be activated.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>( user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User u) {
        User user = userService.deleteUser(u);
        if(user == null)
            return new ResponseEntity<>("User can't be deleted.",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/authority/{id}")
    public ResponseEntity<?> checkAuthority(@PathVariable Long id){
        User user = this.userService.findById(id);
        return new ResponseEntity<>(user.getRoleType(), HttpStatus.OK);

    }
}
