package ch.fhnw.oop2.swissmountainsfx.dao;

/**
 * This factory ensures that only one instance for the data access exists.
 * It is specific for JPA.
 * 
 * @author Linus
 */
public class JpaDaoFactory extends DaoFactory {

    private static final JpaDaoFactory INSTANCE = new JpaDaoFactory();

    private MountainAccess mountainAccess;

    private JpaDaoFactory() {}

    public static JpaDaoFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Use this method to get the data access object to perform interactions
     * on the database.
     * 
     * @return always the same data access object
     */
    @Override
    public MountainAccess getMountainAccessInstance() {
        if (mountainAccess == null) {
            mountainAccess = new MountainAccessImpl();
        }

        return mountainAccess;
    }

}
