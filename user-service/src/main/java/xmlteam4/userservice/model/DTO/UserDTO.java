package xmlteam4.userservice.model.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.userservice.model.RoleTypes;

@Setter
@Getter
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String type;
    private RoleTypes roleType;

    public UserDTO() {
    }
}
