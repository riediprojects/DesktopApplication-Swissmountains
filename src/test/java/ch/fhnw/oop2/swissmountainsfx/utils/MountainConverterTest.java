package ch.fhnw.oop2.swissmountainsfx.utils;

import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainHeightException;
import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainIsolationException;
import ch.fhnw.oop2.swissmountainsfx.exceptions.InvalidMountainProminenceException;
import ch.fhnw.oop2.swissmountainsfx.model.Mountain;
import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class to check if the converting between the models works properly.
 * 
 * @author Linus
 */
public class MountainConverterTest {

    public MountainConverterTest() {
        
    }

    /**
     * Test of createMountainVM method, of class MountainConverter.
     */
    @Test
    public void testCreateMountainVM() {
        Mountain m = new Mountain();
        Long id = new Long(6000);

        m.setMountainId(id);
        m.setMountainname("Testberg");
        m.setHeight(3000.0);
        m.setMountaintype("Nebengipfel");
        m.setRegion("Mittelland");
        m.setCantons("Solothurn");
        m.setRange("");
        m.setMountainisolation(500.0);
        m.setIsolationpoint("!IP");
        m.setProminence(0.02);
        m.setCaption("333");
        m.setProminencepoint("Auch dies ist ein Test, aber ein etwas laengerer String...");
        m.setImagename("1.jpg");

        MountainVM result = MountainConverter.createMountainVM(m);
        assertEquals((long) m.getMountainId(), result.getMountainId());
        assertEquals(m.getMountainname(), result.getMountainname());
        assertEquals(m.getHeight(), result.getHeight(), 0.0001);
        assertEquals(m.getMountaintype(), result.getMountaintype());
        assertEquals(m.getRegion(), result.getRegion());
        assertEquals(m.getCantons(), result.getCantons());
        assertEquals(m.getRange(), result.getRange());
        assertEquals(m.getMountainisolation(), result.getMountainisolation(), 0.0001);
        assertEquals(m.getIsolationpoint(), result.getIsolationpoint());
        assertEquals(m.getProminence(), result.getProminence(), 0.0001);
        assertEquals(m.getCaption(), result.getCaption());
        assertEquals(m.getProminencepoint(), result.getProminencepoint());
        assertEquals(m.getImagename(), result.getImagename());
    }

    /**
     * Test of createMountain method, of class MountainConverter.
     */
    @Test
    public void testCreateMountain() {
        try {
            MountainVM m = new MountainVM();
            Long id = new Long(6000);
            
            m.setMountainId(id);
            m.setMountainname("Testberg");
            m.setHeight(3000.0);
            m.setMountaintype("Nebengipfel");
            m.setRegion("Mittelland");
            m.setCantons("Solothurn");
            m.setRange("");
            m.setMountainisolation(500.0);
            m.setIsolationpoint("!IP");
            m.setProminence(0.02);
            m.setCaption("333");
            m.setProminencepoint("Auch dies ist ein Test, aber ein etwas laengerer String...");
            m.setImagename("1.jpg");
            
            Mountain result = MountainConverter.createMountain(m);
            assertEquals(m.getMountainId(), result.getMountainId(), 0);
            assertEquals(m.getMountainname(), result.getMountainname());
            assertEquals(m.getHeight(), result.getHeight(), 0.0001);
            assertEquals(m.getMountaintype(), result.getMountaintype());
            assertEquals(m.getRegion(), result.getRegion());
            assertEquals(m.getCantons(), result.getCantons());
            assertEquals(m.getRange(), result.getRange());
            assertEquals(m.getMountainisolation(), result.getMountainisolation(), 0.0001);
            assertEquals(m.getIsolationpoint(), result.getIsolationpoint());
            assertEquals(m.getProminence(), result.getProminence(), 0.0001);
            assertEquals(m.getCaption(), result.getCaption());
            assertEquals(m.getProminencepoint(), result.getProminencepoint());
            assertEquals(m.getImagename(), result.getImagename());
        } catch (InvalidMountainHeightException | InvalidMountainIsolationException | InvalidMountainProminenceException ex) {
            fail("Failed on initialization");
        }
    }

}
