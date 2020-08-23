package ch.fhnw.oop2.swissmountainsfx.dao;

import ch.fhnw.oop2.swissmountainsfx.model.Mountain;
import ch.fhnw.oop2.swissmountainsfx.model.MountainVM;
import ch.fhnw.oop2.swissmountainsfx.utils.MountainConverter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Persistence;

/**
 * Specific mountain access implementation for JPA. Use this class to operate on
 * the database.
 *
 * @author Linus
 */
public class MountainAccessImpl implements MountainAccess {

    private final EntityManager em;

    protected MountainAccessImpl() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SwissMountainsPU");
        em = emfactory.createEntityManager();
    }

    @Override
    public List<MountainVM> getAllMountains() {
        Query q = em.createNamedQuery("Mountain.findAll");

        List<Mountain> allMountains = q.getResultList();

        // Convert mountain models to property models
        List<MountainVM> allMountainsVM = new ArrayList();

        allMountains.forEach((mountain) -> {
            allMountainsVM.add(MountainConverter.createMountainVM(mountain));
        });

        return allMountainsVM;
    }

    @Override
    public void addMountain(MountainVM m) {
        Mountain mountain = MountainConverter.createMountain(m);

        addMountain(mountain);
    }

    @Override
    public void updateMountain(MountainVM m) {
        em.getTransaction().begin();

        Mountain mountain = getMountainById(m.getMountainId());

        mountain.setMountainname(m.getMountainname());
        mountain.setCantons(m.getCantons());
        mountain.setCaption(m.getCaption());
        mountain.setHeight(m.getHeight());
        mountain.setIsolationpoint(m.getIsolationpoint());
        mountain.setMountainisolation(m.getMountainisolation());
        mountain.setMountaintype(m.getMountaintype());
        mountain.setProminence(m.getProminence());
        mountain.setProminencepoint(m.getProminencepoint());
        mountain.setRange(m.getRange());
        mountain.setRegion(m.getRegion());

        System.out.println("Update mountain with id: " + m.getMountainId());

        em.getTransaction().commit();
    }

    @Override
    public void deleteMountain(MountainVM m) {
        em.getTransaction().begin();

        Mountain mountain = getMountainById(m.getMountainId());

        System.out.println("Delete mountain with id: " + m.getMountainId());

        em.remove(mountain);

        em.getTransaction().commit();
    }

    @Override
    public void addMountain(Mountain m) {
        em.getTransaction().begin();

        em.persist(m);

        System.out.println("Add mountain with id: " + m.getMountainId());

        em.getTransaction().commit();
    }

    @Override
    public boolean mountainExists(MountainVM m) {

        System.out.println("Check mountain id: " + m.getMountainId());

        Mountain mountain = getMountainById(m.getMountainId());

        if (mountain == null) {
            return false;
        }

        return true;
    }

    private Mountain getMountainById(long mountainId) {
        Query q = em.createNamedQuery("Mountain.findByMountainId").setParameter("mountainId", mountainId);
        List<Mountain> mountainList = q.getResultList();

        if (mountainList.isEmpty()) {
            System.out.println("No mountain with this id found!");
            return null;
        }

        return mountainList.get(0);
    }
}
