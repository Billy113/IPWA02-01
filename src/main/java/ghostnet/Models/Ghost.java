package ghostnet.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Ghost {
    @Id
    @GeneratedValue(generator = "Incremental")
    @GenericGenerator(name = "Incremental", strategy = "org.hibernate.id.IncrementGenerator")
    private Long id;
    private String name;
    private Status status;
    private Double latitude;
    private Double longitude;
    private Integer size;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Hunter hunter;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Hunter getHunter() {
        return hunter;
    }

    public void setHunter(Hunter hunters) {
        this.hunter = hunters;
    }
}
