package xmlteam4.carservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Location {
    private String city;

    public Location() {}
}
