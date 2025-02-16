package ghostnet.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Hunter extends Person {
    private static final Object CascadeType = javax.persistence.CascadeType.ALL;
    private String phone;

    @OneToMany(mappedBy = "hunter", fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
    private List<Ghost> ghosts;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public void setGhosts(List<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

}
