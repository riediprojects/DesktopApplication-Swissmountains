package ch.fhnw.oop2.swissmountainsfx.dao;

/**
 * This abstract factory allows multiple sub factories in case there are
 * different implementations to access the data
 * 
 * @author Linus
 */
public abstract class DaoFactory {

    public abstract MountainAccess getMountainAccessInstance();
}
