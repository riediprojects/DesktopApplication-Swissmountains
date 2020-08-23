package ch.fhnw.oop2.swissmountainsfx.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Generated entity class (Java Persistence) for the objects in the mountains
 * table.
 *
 * @author Linus
 */
@Entity
@Table(name = "MOUNTAINS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mountain.findAll", query = "SELECT m FROM Mountain m")
    , @NamedQuery(name = "Mountain.findByMountainId", query = "SELECT m FROM Mountain m WHERE m.mountainId = :mountainId")
    , @NamedQuery(name = "Mountain.findByMountainname", query = "SELECT m FROM Mountain m WHERE m.mountainname = :mountainname")
    , @NamedQuery(name = "Mountain.findByHeight", query = "SELECT m FROM Mountain m WHERE m.height = :height")
    , @NamedQuery(name = "Mountain.findByMountaintype", query = "SELECT m FROM Mountain m WHERE m.mountaintype = :mountaintype")
    , @NamedQuery(name = "Mountain.findByRegion", query = "SELECT m FROM Mountain m WHERE m.region = :region")
    , @NamedQuery(name = "Mountain.findByCantons", query = "SELECT m FROM Mountain m WHERE m.cantons = :cantons")
    , @NamedQuery(name = "Mountain.findByRange", query = "SELECT m FROM Mountain m WHERE m.range = :range")
    , @NamedQuery(name = "Mountain.findByMountainisolation", query = "SELECT m FROM Mountain m WHERE m.mountainisolation = :mountainisolation")
    , @NamedQuery(name = "Mountain.findByIsolationpoint", query = "SELECT m FROM Mountain m WHERE m.isolationpoint = :isolationpoint")
    , @NamedQuery(name = "Mountain.findByProminence", query = "SELECT m FROM Mountain m WHERE m.prominence = :prominence")
    , @NamedQuery(name = "Mountain.findByProminencePoint", query = "SELECT m FROM Mountain m WHERE m.prominencepoint = :prominencepoint")
    , @NamedQuery(name = "Mountain.findByImagename", query = "SELECT m FROM Mountain m WHERE m.imagename = :imagename")
    , @NamedQuery(name = "Mountain.findByCaption", query = "SELECT m FROM Mountain m WHERE m.caption = :caption")})
public class Mountain implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MOUNTAIN_ID")
    private Long mountainId;
    @Column(name = "MOUNTAINNAME")
    private String mountainname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HEIGHT")
    private Double height;
    @Column(name = "MOUNTAINTYPE")
    private String mountaintype;
    @Column(name = "REGION")
    private String region;
    @Column(name = "CANTONS")
    private String cantons;
    @Column(name = "RANGE")
    private String range;
    @Column(name = "MOUNTAINISOLATION")
    private Double mountainisolation;
    @Column(name = "ISOLATIONPOINT")
    private String isolationpoint;
    @Column(name = "PROMINENCE")
    private Double prominence;
    @Column(name = "CAPTION")
    private String caption;
    @Column(name = "PROMINENCEPOINT")
    private String prominencepoint;
    @Column(name = "IMAGENAME")
    private String imagename;

    public Mountain() {
    }

    public Mountain(Long mountainId) {
        this.mountainId = mountainId;
    }

    public Long getMountainId() {
        return mountainId;
    }

    public void setMountainId(Long mountainId) {
        this.mountainId = mountainId;
    }

    public String getMountainname() {
        return mountainname;
    }

    public void setMountainname(String mountainname) {
        this.mountainname = mountainname;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getMountaintype() {
        return mountaintype;
    }

    public void setMountaintype(String mountaintype) {
        this.mountaintype = mountaintype;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCantons() {
        return cantons;
    }

    public void setCantons(String cantons) {
        this.cantons = cantons;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Double getMountainisolation() {
        return mountainisolation;
    }

    public void setMountainisolation(Double mountainisolation) {
        this.mountainisolation = mountainisolation;
    }

    public String getIsolationpoint() {
        return isolationpoint;
    }

    public void setIsolationpoint(String isolationpoint) {
        this.isolationpoint = isolationpoint;
    }

    public Double getProminence() {
        return prominence;
    }

    public void setProminence(Double prominence) {
        this.prominence = prominence;
    }

    public String getProminencepoint() {
        return prominencepoint;
    }

    public void setProminencepoint(String prominencepoint) {
        this.prominencepoint = prominencepoint;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mountainId != null ? mountainId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mountain)) {
            return false;
        }
        Mountain other = (Mountain) object;
        if ((this.mountainId == null && other.mountainId != null) || (this.mountainId != null && !this.mountainId.equals(other.mountainId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.fhnw.oop2.swissmountainsfx.model.Mountains[ mountainId=" + mountainId + " ]";
    }

}
