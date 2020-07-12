package xmlteam4.userservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@DiscriminatorValue("AGENT")
public class Agent extends User{
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String agencyName;
    @Column
    private String address;
    @Column
    private String businessId;
}
