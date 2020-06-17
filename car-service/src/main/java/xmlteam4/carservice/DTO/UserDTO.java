package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String type;

    public UserDTO() {
    }
}
