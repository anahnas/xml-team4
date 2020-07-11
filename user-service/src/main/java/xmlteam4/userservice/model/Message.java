package xmlteam4.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;

@Entity
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", namespace = "http://localhost:8085/xml-user-service-schema")
@XmlRootElement(name = "messageClass")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @XmlElement
    private Long id;
    @Column
    @XmlElement
    private Long receiverId;
    @Column
    @XmlElement
    private Long senderId;
    @Column
    @XmlElement
    private Date dateSent;
    @Column
    @XmlElement
    private String content;

}
