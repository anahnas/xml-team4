package xmlteam4.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlteam4.userservice.model.Message;

import java.util.ArrayList;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findById(Long id);
    ArrayList<Message> findAll();
    void deleteById(Long id);
    Message save(Message message);

    //@Query(value = "SELECT * FROM Message m WHERE m.receiverId =  ?1 and u.senderId = ?2" )
    ArrayList<Message> findMessageByReceiverIdAndSenderId(Long receiverId, Long senderId);
}
