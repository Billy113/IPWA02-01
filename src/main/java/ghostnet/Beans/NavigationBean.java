package ghostnet.Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NavigationBean {
    public String goToReport() {
        return "/reportNewGhost.xhtml?faces-redirect=true";
    }

    public String goToNetOverview() {
        return "/ghostNetOverview.xhtml?faces-redirect=true";
    }

    public String goToMap() {
        return "/map.xhtml?faces-redirect=true";
    }

    public String goToRegisterForGhost() {
        return "/registerForGhost.xhtml?faces-redirect=true";
    }

    public String goToChangeHunter() {
        return "/changeHunter.xhtml?faces-redirect=true";
    }
}
