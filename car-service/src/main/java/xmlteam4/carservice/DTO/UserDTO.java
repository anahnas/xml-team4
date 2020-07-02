package xmlteam4.carservice.DTO;

import lombok.Getter;
import lombok.Setter;
import xmlteam4.carservice.model.RoleTypes;

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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", roleType=" + roleType +
                '}';
    }
}
