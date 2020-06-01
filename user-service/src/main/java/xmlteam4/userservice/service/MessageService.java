package xmlteam4.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.userservice.model.Message;
import xmlteam4.userservice.repository.MessageRepository;

import java.util.ArrayList;
import java.util.Date;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public ArrayList<Message> getMessages(Long receiverId, Long senderId){
        return this.messageRepository.findMessageByReceiverIdAndSenderId(receiverId, senderId);
    }

    public Message sendMessage(Message message){
        message.setDateSent(new Date());
        return this.messageRepository.save(message);
    }

    public boolean removeMessage(Long id){
        try{
            this.messageRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
