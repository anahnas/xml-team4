package xmlteam4.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long receiverId;
    @Column
    private Long senderId;
    @Column
    private Date dateSent;
    @Column
    private String content;

}
