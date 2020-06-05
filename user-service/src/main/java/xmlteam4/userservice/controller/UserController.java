package xmlteam4.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.userservice.model.BasicUser;
import xmlteam4.userservice.model.Message;
import xmlteam4.userservice.model.User;
import xmlteam4.userservice.service.MessageService;
import xmlteam4.userservice.service.UserService;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private MessageService messageService;

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

    @GetMapping(value="/user/findBasic")
    public ResponseEntity<List<User>> getBasicUsers() {
        List<User> retVal = userService.findBasicUsers();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/user/findOne/{id}")
        public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User retVal = userService.findById(id);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }



    @GetMapping(value="/user/all")
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

    @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@RequestBody User u) {
        User user = userService.deleteUser(u);
        if(user == null)
            return new ResponseEntity<>("User can't be deleted.",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /////////////////////////////  Message  /////////////////////////////

    @GetMapping(value="/messages/{receiverId}/{senderId}")
    public ResponseEntity<?> getMessages(@PathVariable("receiverId") Long receiverId, @PathVariable("senderId") Long senderId){
        ArrayList<Message> retVal = messageService.getMessages(receiverId, senderId);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping(value="/message")
    public ResponseEntity<?> sendMessage(@RequestBody Message message){
        Message retVal = this.messageService.sendMessage(message);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value="/messages/{id}")
    public ResponseEntity<?> removeMessage(@PathVariable("id") Long id){
        if(messageService.removeMessage(id))
            return new ResponseEntity<>("Deleted.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Error deleting message with id " + id +".", HttpStatus.BAD_REQUEST);
    }

}
