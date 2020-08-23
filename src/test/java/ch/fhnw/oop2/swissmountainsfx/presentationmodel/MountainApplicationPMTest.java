package ch.fhnw.oop2.swissmountainsfx.presentationmodel;

import ch.fhnw.oop2.swissmountainsfx.dao.JpaDaoFactory;
import ch.fhnw.oop2.swissmountainsfx.dao.MountainAccess;
import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the MountainApplicationPM class.<br>
 * Needs a properly setup database in order to work.
 *
 * @author Linus, Manuel
 */
public class MountainApplicationPMTest {

    // Database connection
    MountainAccess mountainAccess;

    public MountainApplicationPMTest() {
        mountainAccess = JpaDaoFactory.getInstance().getMountainAccessInstance();
    }

    /**
     * Test of deleteSelectedMountain method, of class MountainApplicationPM.
     */
    @Test
    public void testDeleteSelectedMountain() {

        MountainApplicationPM instance = new MountainApplicationPM();
        instance.addNewMountain();

        MountainVM m = instance.getMountainData().get(instance.getMountainData().size() - 1);
        assertTrue(instance.getMountainData().contains(m));

        mountainAccess.addMountain(m);

        assertTrue(mountainAccess.mountainExists(m));

        instance.deleteSelectedMountain(m);

        assertFalse(instance.getMountainData().contains(m));
        assertFalse(mountainAccess.mountainExists(m));
    }

    /**
     * Test of saveAllMountains method, of class MountainApplicationPM.
     */
    @Test
    public void testSaveAllMountains() {
        MountainApplicationPM instance = new MountainApplicationPM();

        instance.addNewMountain();
        MountainVM m1 = instance.getMountainData().get(instance.getMountainData().size() - 1);

        instance.addNewMountain();
        MountainVM m2 = instance.getMountainData().get(instance.getMountainData().size() - 1);

        int numberOfMountains = mountainAccess.getAllMountains().size();

        instance.saveAllMountains();
        assertEquals(mountainAccess.getAllMountains().size(), numberOfMountains + 2);

        assertEquals(instance.getIdsOfUnsavedMountains().size(), 0);
        instance.getMountainData().get(instance.getMountainData().size() - 1).setCantons("change");
        assertEquals(instance.getIdsOfUnsavedMountains().size(), 1);

        instance.deleteSelectedMountain(m1);
        instance.deleteSelectedMountain(m2);
    }

    /**
     * DONE Test of saveMountain method, of class MountainApplicationPM.
     */
    @Test
    public void testSaveMountain() {
        MountainVM m = new MountainVM();
        m.setMountainId(5005);

        MountainApplicationPM instance = new MountainApplicationPM();

        assertFalse(mountainAccess.mountainExists(m));
        instance.saveMountain(m);
        assertTrue(mountainAccess.mountainExists(m));

        instance.deleteSelectedMountain(m);
    }

    /**
     * DONE Test of addNewMountain method, of class MountainApplicationPM.
     */
    @Test
    public void testAddNewMountain() {

        MountainApplicationPM instance = new MountainApplicationPM();

        int listSize = instance.getMountainData().size();

        instance.addNewMountain();
        assertEquals(instance.getMountainData().size(), listSize + 1);
        assertEquals(mountainAccess.getAllMountains().size(), listSize);
    }

    /**
     * DONE Test of sortByValue method, of class MountainApplicationPM.
     */
    @Test
    public void testSortByValue() {

        MountainApplicationPM instance = new MountainApplicationPM();
        ObservableList<MountainVM> result = instance.getMountainData();

        // Test name sorting
        instance.sortByValue("Name aufsteigend");
        MountainVM topN1 = result.get(0);
        MountainVM topN2 = result.get(1);
        instance.sortByValue("Name absteigend");
        MountainVM bottomN1 = result.get(result.size() - 1);
        MountainVM bottomN2 = result.get(result.size() - 2);

        assertEquals(topN1, bottomN1);
        assertEquals(topN2, bottomN2);

        // Test height sorting
        instance.sortByValue("H�he aufsteigend");
        MountainVM topH1 = result.get(0);
        MountainVM topH2 = result.get(1);
        instance.sortByValue("H�he absteigend");
        MountainVM bottomH1 = result.get(result.size() - 1);
        MountainVM bottomH2 = result.get(result.size() - 2);

        assertEquals(topH1, bottomH1);
        assertEquals(topH2, bottomH2);

        // Test region sorting
        instance.sortByValue("Gebiet absteigend");
        MountainVM topG1 = result.get(0);
        MountainVM topG2 = result.get(1);
        instance.sortByValue("Gebiet aufsteigend");
        MountainVM bottomG1 = result.get(result.size() - 1);
        MountainVM bottomG2 = result.get(result.size() - 2);

        assertEquals(topG1, bottomG1);
        assertEquals(topG2, bottomG2);

    }

}
