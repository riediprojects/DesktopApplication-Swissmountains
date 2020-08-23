package ch.fhnw.oop2.swissmountainsfx.model;

import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainHeightException;
import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainIsolationException;
import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainProminenceException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Mountain model class with properties, used for the UI bindings.
 *
 * @author Linus
 */
public class MountainVM {

    private final LongProperty mountainId = new SimpleLongProperty();
    private final StringProperty mountainname = new SimpleStringProperty();
    private final DoubleProperty height = new SimpleDoubleProperty();
    private final StringProperty mountaintype = new SimpleStringProperty();
    private final StringProperty region = new SimpleStringProperty();
    private final StringProperty cantons = new SimpleStringProperty();
    private final StringProperty range = new SimpleStringProperty();
    private final DoubleProperty mountainisolation = new SimpleDoubleProperty();
    private final StringProperty isolationpoint = new SimpleStringProperty();
    private final DoubleProperty prominence = new SimpleDoubleProperty();
    private final StringProperty caption = new SimpleStringProperty();
    private final StringProperty prominencepoint = new SimpleStringProperty();
    private final StringProperty imagename = new SimpleStringProperty();

    private final double MAX_HEIGHT = 100000;
    private final double MAX_ISOLATION = 1000;
    private final double MAX_PROMINENCE = 10000;

    public MountainVM() {
        try {
            // Init default values: Needed that bindings and sorting work properly
            // event if the user does not insert any values
            this.setMountainname("");
            this.setHeight(0);
            this.setMountaintype("");
            this.setRegion("");
            this.setCantons("");
            this.setRange("");
            this.setMountainisolation(0);
            this.setIsolationpoint("");
            this.setProminence(0);
            this.setCantons("");
            this.setProminencepoint("");
            this.setImagename("");
        } catch (Exception ex) {
            System.out.println("Invalid default values!");
        }
    }

    public LongProperty mountainIdProperty() {
        return mountainId;
    }

    public long getMountainId() {
        return mountainId.get();
    }

    public void setMountainId(long mountainId) {
        this.mountainId.set(mountainId);
    }

    public StringProperty mountainnameProperty() {
        return mountainname;
    }

    public String getMountainname() {
        return mountainname.get();
    }

    public void setMountainname(String mountainname) {
        this.mountainname.set(mountainname);
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public double getHeight() {
        return height.get();
    }

    public void setHeight(double height) throws InvalidMountainHeightException {
        if (height < 0) {
            throw new InvalidMountainHeightException("Eine negative Höhe ist nicht erlaubt.");
        }
        if (height > MAX_HEIGHT) {
            throw new InvalidMountainHeightException("Eine Höhe grösser als " + MAX_HEIGHT + " ist nicht erlaubt.");
        }

        this.height.set(height);
    }

    public StringProperty mountaintypeProperty() {
        return mountaintype;
    }

    public String getMountaintype() {
        return mountaintype.get();
    }

    public void setMountaintype(String mountaintype) {
        this.mountaintype.set(mountaintype);
    }

    public StringProperty regionProperty() {
        return region;
    }

    public String getRegion() {
        return region.get();
    }

    public void setRegion(String region) {
        this.region.set(region);
    }

    public StringProperty cantonsProperty() {
        return cantons;
    }

    public String getCantons() {
        return cantons.get();
    }

    public void setCantons(String cantons) {
        this.cantons.set(cantons);
    }

    public StringProperty rangeProperty() {
        return range;
    }

    public String getRange() {
        return range.get();
    }

    public void setRange(String range) {
        this.range.set(range);
    }

    public DoubleProperty mountainisolationProperty() {
        return mountainisolation;
    }

    public double getMountainisolation() {
        return mountainisolation.get();
    }

    public void setMountainisolation(double mountainisolation) throws InvalidMountainIsolationException {
        if (mountainisolation < 0) {
            throw new InvalidMountainIsolationException("Eine negative Dominanz ist nicht erlaubt.");
        }
        if (mountainisolation > MAX_ISOLATION) {
            throw new InvalidMountainIsolationException("Eine Dominanz grösser als " + MAX_ISOLATION + " ist nicht erlaubt.");
        }

        this.mountainisolation.set(mountainisolation);
    }

    public StringProperty isolationpointProperty() {
        return isolationpoint;
    }

    public String getIsolationpoint() {
        return isolationpoint.get();
    }

    public void setIsolationpoint(String isolationpoint) {
        this.isolationpoint.set(isolationpoint);
    }

    public DoubleProperty prominenceProperty() {
        return prominence;
    }

    public double getProminence() {
        return prominence.get();
    }

    public void setProminence(double prominence) throws InvalidMountainProminenceException {
        if (prominence < 0) {
            throw new InvalidMountainProminenceException("Eine negative Schartenharthen ist nicht erlaubt.");
        }
        if (prominence > MAX_PROMINENCE) {
            throw new InvalidMountainProminenceException("Eine Schartenöhe grösser als " + MAX_PROMINENCE + " ist nicht erlaubt.");
        }

        this.prominence.set(prominence);
    }

    public StringProperty captionProperty() {
        return caption;
    }

    public String getCaption() {
        return caption.get();
    }

    public void setCaption(String caption) {
        this.caption.set(caption);
    }

    public StringProperty prominencepointProperty() {
        return prominencepoint;
    }

    public String getProminencepoint() {
        return prominencepoint.get();
    }

    public void setProminencepoint(String prominencepoint) {
        this.prominencepoint.set(prominencepoint);
    }

    public StringProperty imagenameProperty() {
        return imagename;
    }

    public String getImagename() {
        return imagename.get();
    }

    public void setImagename(String imagepath) {
        this.imagename.set(imagepath);
    }

}
