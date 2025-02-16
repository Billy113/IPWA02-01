package ghostnet.Beans;

import ghostnet.Models.Ghost;
import ghostnet.Models.Status;
import ghostnet.Repositories.GhostRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "GhostsOverviewBean")
@SessionScoped
public class GhostOverviewBean {
    private List<Ghost> ghosts;
    private final GhostRepository ghostRepository;

    public GhostOverviewBean() {
        ghostRepository = new GhostRepository();
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public List<Ghost> getReportedGhosts() {
        return ghosts.stream().filter(ghost -> ghost.getStatus().equals(Status.REPORTED)).collect(Collectors.toList());
    }

    public List<Ghost> getMissingGhosts() {
        return ghosts.stream().filter(ghost -> ghost.getStatus().equals(Status.MISSING)).collect(Collectors.toList());
    }

    public List<Ghost> getRetrievedGhosts() {
        return ghosts.stream().filter(ghost -> ghost.getStatus().equals(Status.RETRIEVED)).collect(Collectors.toList());
    }

    public List<Ghost> getRetrieving() {
        return ghosts.stream().filter(ghost -> ghost.getStatus().equals(Status.RETRIEVING)).collect(Collectors.toList());
    }

    public void loadGhosts() {
        ghosts = ghostRepository.selectAllGhosts();
    }

    public String status(Status status) {
        switch (status) {
            case REPORTED:
                return "Gemeldet";
            case MISSING:
                return "Verschollen";
            case RETRIEVED:
                return "Geborgen";
            case RETRIEVING:
                return "In Bergung";
        }
        return "Fehler";
    }
}