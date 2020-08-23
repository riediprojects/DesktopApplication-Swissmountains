package ch.fhnw.oop2.swissmountainsfx.dao;

import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainHeightException;
import ch.fhnw.oop2.swissmountainsfx.model.Mountain;
import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class to check if the database operations work as expected.<br>
 * Needs a properly setup database in order to work.
 *
 * @author Linus
 */
public class MountainAccessTest {

    // Database connection
    MountainAccess mountainAccess;

    public MountainAccessTest() {
        mountainAccess = JpaDaoFactory.getInstance().getMountainAccessInstance();
    }

    /**
     * Test of getAllMountains method, of class MountainAccess.
     */
    @Test
    public void testGetAllMountains() {
        MountainVM m = new MountainVM();
        m.setMountainId(7000);

        int numberOfMountains = mountainAccess.getAllMountains().size();

        mountainAccess.addMountain(m);

        assertEquals(mountainAccess.getAllMountains().size(), numberOfMountains + 1);

        // Clean db
        mountainAccess.deleteMountain(m);
    }

    /**
     * Test of addMountain method, of class MountainAccess.
     */
    @Test
    public void testAddMountain_MountainVM() {
        MountainVM m = new MountainVM();
        m.setMountainId(7000);

        assertFalse(mountainAccess.mountainExists(m));

        mountainAccess.addMountain(m);

        assertTrue(mountainAccess.mountainExists(m));

        // Clean db
        mountainAccess.deleteMountain(m);
    }

    /**
     * Test of addMountain method, of class MountainAccess.
     */
    @Test
    public void testAddMountain_Mountain() {
        MountainVM m = new MountainVM();
        m.setMountainId(7000);

        assertFalse(mountainAccess.mountainExists(m));

        mountainAccess.addMountain(m);

        assertTrue(mountainAccess.mountainExists(m));

        // Clean db
        mountainAccess.deleteMountain(m);
    }

    /**
     * Test of updateMountain method, of class MountainAccess.
     */
    @Test
    public void testUpdateMountain() {
        long id = 7000;

        MountainVM m = new MountainVM();
        try {
            m.setMountainId(7000);
            m.setMountainname("Test");
            m.setHeight(300.0);
        } catch (InvalidMountainHeightException ex) {
            fail("Failed on initialization");
        }

        mountainAccess.addMountain(m);

        List<MountainVM> allMountains = mountainAccess.getAllMountains();
        MountainVM mountainToUpdate = allMountains.stream()
                .filter((MountainVM mn) -> mn.getMountainId() == id)
                .limit(1)
                .findFirst()
                .orElse(null);

        assertEquals("Test", mountainToUpdate.getMountainname());
        assertEquals(300, mountainToUpdate.getHeight(), 0.0001);

        try {
            mountainToUpdate.setMountainname("Neuer Name");
            mountainToUpdate.setHeight(500.4);
        } catch (InvalidMountainHeightException ex) {
            fail("Failed on initialization");
        }

        mountainAccess.updateMountain(mountainToUpdate);

        allMountains = mountainAccess.getAllMountains();
        MountainVM updatedMountain = allMountains.stream()
                .filter((MountainVM mn) -> mn.getMountainId() == id)
                .limit(1)
                .findFirst()
                .orElse(null);

        assertEquals("Neuer Name", updatedMountain.getMountainname());
        assertEquals(500.4, updatedMountain.getHeight(), 0.0001);

        // Clean db
        mountainAccess.deleteMountain(m);
    }

    /**
     * Test of deleteMountain method, of class MountainAccess.
     */
    @Test
    public void testDeleteMountain() {
        MountainVM m = new MountainVM();
        m.setMountainId(7000);

        mountainAccess.addMountain(m);
        assertTrue(mountainAccess.mountainExists(m));

        mountainAccess.deleteMountain(m);
        assertFalse(mountainAccess.mountainExists(m));
    }

    /**
     * Test of mountainExists method, of class MountainAccess.
     */
    @Test
    public void testMountainExists() {
        MountainVM m = new MountainVM();
        m.setMountainId(7000);

        MountainVM m2 = new MountainVM();
        m2.setMountainId(5000);

        mountainAccess.addMountain(m);
        assertTrue(mountainAccess.mountainExists(m));
        assertFalse(mountainAccess.mountainExists(m2));

        // Clean db
        mountainAccess.deleteMountain(m);
    }

    public class MountainAccessImpl implements MountainAccess {

        @Override
        public List<MountainVM> getAllMountains() {
            return null;
        }

        @Override
        public void addMountain(MountainVM m) {
        }

        @Override
        public void addMountain(Mountain m) {
        }

        @Override
        public void updateMountain(MountainVM mountain) {
        }

        @Override
        public void deleteMountain(MountainVM mountain) {
        }

        @Override
        public boolean mountainExists(MountainVM mountain) {
            return false;
        }
    }

}
