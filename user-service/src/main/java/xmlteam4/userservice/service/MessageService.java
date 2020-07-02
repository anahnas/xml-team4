package xmlteam4.userservice.service;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlteam4.userservice.client.CarFeignClient;
import xmlteam4.userservice.client.RentFeignClient;
import xmlteam4.userservice.model.BasicUser;
import xmlteam4.userservice.model.DTO.CarDTOBasic;
import xmlteam4.userservice.model.DTO.MessageDTO;
import xmlteam4.userservice.model.DTO.Rent;
import xmlteam4.userservice.model.Message;
import xmlteam4.userservice.model.User;
import xmlteam4.userservice.repository.BasicUserRepository;
import xmlteam4.userservice.repository.MessageRepository;
import xmlteam4.userservice.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentFeignClient rentFeignClient;

    @Autowired
    private CarFeignClient carFeignClient;


    public ArrayList<MessageDTO> getMessages(Long receiverId){
        ArrayList<Message> messages = this.messageRepository.findMessageByReceiverId(receiverId);
        ArrayList<MessageDTO> messageDTOs = new ArrayList<>();


        for(Message message : messages){
            messageDTOs.add(this.message2MessageDTO(message));
        }
        return messageDTOs;
    }

    public ArrayList<Message> getMessages(Long receiverId, Long senderId){
        return this.messageRepository.findMessageByReceiverIdAndSenderId(receiverId, senderId);
    }

    public MessageDTO sendMessage(MessageDTO messageDTO, Long senderId){

        Long receiverId = this.userRepository.findByUsername(messageDTO.getReceiver()).getId();
        if(userCanSend(senderId, receiverId)){
            Message message = new Message();
            message.setContent(messageDTO.getContent());
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setDateSent(new Date());
            return this.message2MessageDTO(this.messageRepository.save(message));
        }
        return null;
    }

    public boolean userCanSend(Long senderId, Long receiverId){
        List<Rent> paidRents = this.rentFeignClient.getPaid();
        for(Rent rent : paidRents){
            if(rent.getClientId().equals(senderId)){
                CarDTOBasic carDTOBasic = this.carFeignClient.basicCar(rent.getCarId());
                if(carDTOBasic.getOwnerId() != null && carDTOBasic.getOwnerId().equals(receiverId)){
                    return true;
                }
            }
            if(rent.getClientId().equals(receiverId)){
                CarDTOBasic carDTOBasic = this.carFeignClient.basicCar(rent.getCarId());
                if(carDTOBasic.getOwnerId() != null && carDTOBasic.getOwnerId().equals(senderId)){
                    return true;
                }
            }
        }
        return false;
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

    private MessageDTO message2MessageDTO(Message message){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setContent(message.getContent());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        if(message.getDateSent() != null)
            messageDTO.setTimeSent(dateFormat.format(message.getDateSent()));
        messageDTO.setReceiver(this.userRepository.getOne(message.getReceiverId()).getUsername());
        messageDTO.setSender(this.userRepository.getOne(message.getSenderId()).getUsername());
        return messageDTO;
    }
}
