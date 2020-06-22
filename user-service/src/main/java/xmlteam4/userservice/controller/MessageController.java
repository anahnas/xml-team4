package xmlteam4.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlteam4.userservice.model.DTO.MessageDTO;
import xmlteam4.userservice.model.Message;
import xmlteam4.userservice.service.MessageService;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<?> getMessages(@RequestHeader("receiverId") Long receiverId){
        ArrayList<MessageDTO> retVal = messageService.getMessages(receiverId);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    /*@GetMapping(value="/{receiverId}/{senderId}")
    public ResponseEntity<?> getMessages(@PathVariable("receiverId") Long receiverId, @PathVariable("senderId") Long senderId){
        ArrayList<Message> retVal = messageService.getMessages(receiverId, senderId);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestHeader("senderId") Long senderId, @RequestBody MessageDTO messageDTO){
        MessageDTO retVal = this.messageService.sendMessage(messageDTO, senderId);
        if(retVal != null)
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> removeMessage(@PathVariable("id") Long id){
        if(messageService.removeMessage(id))
            return new ResponseEntity<>("Deleted.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Error deleting message with id " + id +".", HttpStatus.BAD_REQUEST);
    }
}
