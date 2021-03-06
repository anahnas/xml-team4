package xmlteam4.userservice.model.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.userservice.model.Message;

@Getter
@Setter
public class MessageDTO {
    private Long id;
    private String receiver;
    private String sender;
    private String timeSent;
    private String content;



    public MessageDTO() {
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", timeSent='" + timeSent + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
