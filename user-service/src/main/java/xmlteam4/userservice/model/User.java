package xmlteam4.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="user_table")
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;

    @Column(name="dtype", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private RoleTypes roleType;

    @Column(nullable = true)
    private boolean blocked;

    //private Set<Authority> authorities;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleType=" + roleType +
                ", blocked=" + blocked +
                '}';
    }
}
