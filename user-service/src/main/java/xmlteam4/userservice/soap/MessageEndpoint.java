package xmlteam4.userservice.soap;

import localhost._8085.xml_user_service_schema.GetMessagesRequest;
import localhost._8085.xml_user_service_schema.GetMessagesResponse;
import localhost._8085.xml_user_service_schema.MessageRequest;
import localhost._8085.xml_user_service_schema.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xmlteam4.userservice.model.BasicUser;
import xmlteam4.userservice.model.DTO.MessageDTO;
import xmlteam4.userservice.model.Message;
import xmlteam4.userservice.model.User;
import xmlteam4.userservice.repository.BasicUserRepository;
import xmlteam4.userservice.repository.MessageRepository;
import xmlteam4.userservice.repository.UserRepository;
import xmlteam4.userservice.service.MessageService;
import xmlteam4.userservice.service.UserService;

import java.text.ParseException;
import java.util.List;

@Endpoint
public class MessageEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8085/xml-user-service-schema";

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BasicUserRepository userRepository;
    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "messageRequest")
    @ResponsePayload
    public MessageResponse newMessage(@RequestPayload MessageRequest request) throws ParseException {
        System.out.println("SOAP - Send message.");
        System.out.println("MESSAGE REQ: " + request.getContent() + ", " + request.getDateSent() + ", " + request.getReciever() + ", " + request.getSender() );

        MessageResponse response = new MessageResponse();
        MessageDTO messageDto = new MessageDTO();
        messageDto.setContent(request.getContent());
        messageDto.setTimeSent(request.getDateSent());
        messageDto.setSender(request.getSender());
        messageDto.setReceiver(request.getReciever());

        //SENDER ID ZAKUCAN, PROVJERA
        this.messageService.sendAgentMessage(messageDto);

        System.out.println("***DODJE LI");
        //response.setMessageId(message.getId());
        // System.out.println("KOJI JE ID PORUKE: " + message.getId());
         return response;
    }
    //nova metoda s drugim localpartom do te, vrti sve pporuke od tog agenta i strpaj
    // i vrati u response kad preuzmemo iz baze kroz response saljemo listu poruka

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessagesRequest")
    @ResponsePayload
    public GetMessagesResponse getAllMessages(@RequestPayload GetMessagesRequest request) throws ParseException {
        System.out.println("SOAP - Get All Messages");

        GetMessagesResponse response = new GetMessagesResponse();
        List<Message> messages = this.messageRepository.findAll();

        for(Message message : messages){
            if(message.getReceiverId().equals(Long.valueOf(request.getSender()))) {
                System.out.println("USLO U IF");

                localhost._8085.xml_user_service_schema.Message messageSoap = new localhost._8085.xml_user_service_schema.Message();
                messageSoap.setId(message.getId());
                messageSoap.setContent(message.getContent());
                messageSoap.setDateSent(message.getDateSent().toString());
                messageSoap.setReciever("5");
                messageSoap.setSender(message.getSenderUsername());
                try{
                // messageSoap.setSender(userService.findById(message.getSenderId()).getUsername());
                    User novi = userService.findById(message.getSenderId());
                    //System.out.println("NOVI" + novi.getUsername());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                System.out.println("MESSAGE SOAP CONTENT:" + messageSoap.getContent() +", " + messageSoap.getSender() + ", " + messageSoap.getReciever());


                System.out.println("MESSAGE SOAP CONTENT:" + messageSoap.getContent() +", " + messageSoap.getSender() + ", " + messageSoap.getReciever());

                response.getMessage().add(messageSoap);
                System.out.println("RESPONSE " + response.toString());
            }
        }
        System.out.println("***DODJE LI DO KRAJA GET ALL MESSAGES");


        return response;
    }

}
