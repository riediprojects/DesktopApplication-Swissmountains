package ch.fhnw.oop2.swissmountainsfx.dao;

import ch.fhnw.oop2.swissmountainsfx.model.Mountain;
import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import java.util.List;

/**
 * Basic interface for operations on a datasource e.g. database for mountain
 * models.<br>
 * Working with this interface allows to change specific implementations if 
 * different datasources are needed.
 * 
 * @author Linus
 */
public interface MountainAccess {

    /**
     * This method returns a list with all mountains that exist in the
     * datasource.
     *
     * @return List with all mountains as property models (not entities)
     */
    List<MountainVM> getAllMountains();

    /**
     * Adds the given mountain with all values including the id as a new object
     * to the datasource.
     *
     * @param m Mountain to add
     */
    void addMountain(MountainVM m);

    /**
     * Additional add method for mountain entities that the dataloader does not
     * need to covert objects 2 times.
     *
     * @param m Mountain to add
     */
    void addMountain(Mountain m);

    /**
     * Replaces the mountain data (for the mountain with the same id) of the
     * given mountain with data for this mountain in the datasource.
     *
     * @param mountain Mountain to update
     */
    void updateMountain(MountainVM mountain);

    /**
     * Deletes the given mountain from the datasource.
     *
     * @param mountain Mountain to delete
     */
    void deleteMountain(MountainVM mountain);

    /**
     * Use this method to check if the given mountain already exists in the
     * datasource. The mountain gets checked by its id.
     *
     * @param mountain Mountain to check
     * @return true if the mountain exists
     */
    boolean mountainExists(MountainVM mountain);
}
