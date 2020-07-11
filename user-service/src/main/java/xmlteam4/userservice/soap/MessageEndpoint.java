package xmlteam4.userservice.soap;

import localhost._8085.xml_user_service_schema.MessageRequest;
import localhost._8085.xml_user_service_schema.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xmlteam4.userservice.model.DTO.MessageDTO;
import xmlteam4.userservice.model.Message;
import xmlteam4.userservice.service.MessageService;

import java.text.ParseException;

@Endpoint
public class MessageEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8085/xml-user-service-schema";

    @Autowired
    private MessageService messageService;

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

}
