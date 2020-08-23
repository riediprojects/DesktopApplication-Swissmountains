package ch.fhnw.oop2.swissmountainsfx.utils;

import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainHeightException;
import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainIsolationException;
import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainProminenceException;
import ch.fhnw.oop2.swissmountainsfx.model.Mountain;
import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use this class to convert the mountain models. This is needed because there
 * is a JPA entity model for the database and a property model for the UI
 * bindings.
 *
 * @author Linus
 */
public class MountainConverter {

    /**
     * Returns a new mountain property object with the same values as the given
     * mountain entity object
     *
     * @param m Mountain to convert
     * @return New Mountain with the same values
     */
    public static MountainVM createMountainVM(Mountain m) {
        MountainVM mountainVM = new MountainVM();

        try {
            mountainVM.setMountainId(m.getMountainId());
            mountainVM.setMountainname(m.getMountainname());
            mountainVM.setHeight(m.getHeight());
            mountainVM.setMountaintype(m.getMountaintype());
            mountainVM.setRegion(m.getRegion());
            mountainVM.setCantons(m.getCantons());
            mountainVM.setRange(m.getRange());
            mountainVM.setMountainisolation(m.getMountainisolation());
            mountainVM.setIsolationpoint(m.getIsolationpoint());
            mountainVM.setProminence(m.getProminence());
            mountainVM.setCaption(m.getCaption());
            mountainVM.setProminencepoint(m.getProminencepoint());
            mountainVM.setImagename(m.getImagename());

        } catch (InvalidMountainHeightException | InvalidMountainIsolationException | InvalidMountainProminenceException ex) {
            Logger.getLogger(MountainConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mountainVM;
    }

    /**
     * Returns a new mountain entity object with the same values as the given
     * mountain property object
     *
     * @param m Mountain to convert
     * @return New Mountain with the same values
     */
    public static Mountain createMountain(MountainVM m) {
        Mountain mountain = new Mountain();

        mountain.setMountainId(m.getMountainId());
        mountain.setMountainname(m.getMountainname());
        mountain.setHeight(m.getHeight());
        mountain.setMountaintype(m.getMountaintype());
        mountain.setRegion(m.getRegion());
        mountain.setCantons(m.getCantons());
        mountain.setRange(m.getRange());
        mountain.setMountainisolation(m.getMountainisolation());
        mountain.setIsolationpoint(m.getIsolationpoint());
        mountain.setProminence(m.getProminence());
        mountain.setCaption(m.getCaption());
        mountain.setProminencepoint(m.getProminencepoint());
        mountain.setImagename(m.getImagename());

        return mountain;
    }
}
