package ghostnet.Beans;

import ghostnet.Models.Ghost;
import ghostnet.Repositories.GhostRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "MapBean")
@ViewScoped
public class MapBean implements Serializable {
    private final GhostRepository ghostRepository = new GhostRepository();
    private List<Ghost> ghosts;
    public MapBean() {
        ghosts = ghostRepository.selectAllGhosts();
    }
    public List<Ghost> getGhosts() {
        return ghosts;
    }
    public void setGhosts(List<Ghost> ghosts) {
        this.ghosts = ghosts;
    }
}
