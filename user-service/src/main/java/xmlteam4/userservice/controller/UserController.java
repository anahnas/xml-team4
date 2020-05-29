package xmlteam4.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.userservice.model.BasicUser;
import xmlteam4.userservice.model.User;
import xmlteam4.userservice.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    // ADMIN //
    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    public ResponseEntity<?> changeRole(@RequestBody User u) {
        User user = userService.addNewUser(u);
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);

        return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
    }

}
