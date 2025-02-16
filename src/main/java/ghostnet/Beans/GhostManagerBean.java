package ghostnet.Beans;

import ghostnet.Repositories.GhostRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "GhostManagerBean")
@SessionScoped
public class GhostManagerBean {
    private String ghostName;
    private Integer ghostSize;
    private Long selectedGhostId;
    private Double latitude;
    private Double longitude;
    private String hunterName;
    private String hunterPhone;

    private final GhostRepository ghostRepository;

    public GhostManagerBean() {
        ghostRepository = new GhostRepository();
    }

    public String addGhost() {
        ghostRepository.insertGhost(ghostName, latitude, longitude, ghostSize);
        return "/ghostNetOverview.xhtml?faces-redirect=true";
    }

    public String registerHunter() {
        if (hunterName == null || hunterName.trim().isEmpty() || 
            hunterPhone == null || hunterPhone.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Fehler", "Bitte füllen Sie alle Felder aus."));
            return null;
        }
        
        ghostRepository.registerHunter(hunterName, hunterPhone, selectedGhostId);
        return "/ghostNetOverview.xhtml?faces-redirect=true";
    }

    public String changeHunter() {
        if (hunterName == null || hunterName.trim().isEmpty() || 
            hunterPhone == null || hunterPhone.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Fehler", "Bitte füllen Sie alle Felder aus."));
            return null;
        }
        
        ghostRepository.changeHunter(hunterName, hunterPhone, selectedGhostId);
        return "/ghostNetOverview.xhtml?faces-redirect=true";
    }

    public String markAsRetrieved() {
        ghostRepository.markAsRetrieved(selectedGhostId);
        return "/ghostNetOverview.xhtml?faces-redirect=true";
    }

    public String markAsMissing() {
        ghostRepository.markAsMissing(selectedGhostId);
        return "/ghostNetOverview.xhtml?faces-redirect=true";
    }

    public String getGhostName() {
        return ghostName;
    }

    public void setGhostName(String ghostName) {
        this.ghostName = ghostName;
    }

    public Integer getGhostSize() {
        return ghostSize;
    }

    public void setGhostSize(Integer ghostSize) {
        this.ghostSize = ghostSize;
    }

    public Long getSelectedGhostId() {
        return selectedGhostId;
    }

    public void setSelectedGhostId(Long selectedGhostId) {
        this.selectedGhostId = selectedGhostId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getHunterName() {
        return hunterName;
    }

    public void setHunterName(String hunterName) {
        this.hunterName = hunterName;
    }

    public String getHunterPhone() {
        return hunterPhone;
    }

    public void setHunterPhone(String hunterPhone) {
        this.hunterPhone = hunterPhone;
    }
}
