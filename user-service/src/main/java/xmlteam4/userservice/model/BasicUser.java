package xmlteam4.userservice.model;

import javax.persistence.Entity;

@Entity
public class BasicUser extends User{
    boolean isBlocked;
    boolean canRent;
    boolean canRate;

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isCanRent() {
        return canRent;
    }

    public void setCanRent(boolean canRent) {
        this.canRent = canRent;
    }

    public boolean isCanRate() {
        return canRate;
    }

    public void setCanRate(boolean canRate) {
        this.canRate = canRate;
    }

}
