package ghostnet.Repositories;

import ghostnet.Models.Ghost;
import ghostnet.Models.Hunter;
import ghostnet.Models.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GhostRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostParty");

    public GhostRepository() {
    }

    @SuppressWarnings("unchecked")
    public List<Ghost> selectAllGhosts() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT DISTINCT g FROM Ghost g LEFT JOIN FETCH g.hunter");
            return (List<Ghost>) query.getResultList();
        } finally {
            em.close();
        }
    }

    public void insertGhost(String ghostName, Double latitude, Double longitude, Integer size) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Ghost ghost = new Ghost();
        if (!ghostName.isEmpty()) {
            ghost.setName(ghostName);
        } else {
            ghost.setName("Unbekannt");
        }
        ghost.setLatitude(latitude);
        ghost.setLongitude(longitude);
        ghost.setSize(size);
        ghost.setStatus(Status.REPORTED);
        tx.begin();
        em.persist(ghost);
        tx.commit();
        em.close();
    }

    public Ghost selectGhost(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ghost ghost = em.find(Ghost.class, id);
            tx.commit();
            return ghost;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    public void registerHunter(String name, String number, Long ghostId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ghost ghost = em.find(Ghost.class, ghostId);
            if (ghost == null) {
                throw new IllegalArgumentException("Ghost not found with ID: " + ghostId);
            }
            
            Hunter hunter = new Hunter();
            hunter.setName(name != null ? name : "Unbekannt");
            hunter.setPhone(number != null ? number : "Keine Nummer");
            
            ghost.setStatus(Status.RETRIEVING);
            ghost.setHunter(hunter);
            
            List<Ghost> ghosts = new ArrayList<>();
            ghosts.add(ghost);
            hunter.setGhosts(ghosts);
            
            em.persist(hunter);
            em.merge(ghost);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void changeHunter(String name, String number, Long ghostId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ghost ghost = em.find(Ghost.class, ghostId);
            if (ghost == null) {
                throw new IllegalArgumentException("Ghost not found with ID: " + ghostId);
            }
            
            Hunter hunter = new Hunter();
            hunter.setName(name != null ? name : "Unbekannt");
            hunter.setPhone(number != null ? number : "Keine Nummer");
            
            ghost.setHunter(hunter);
            
            List<Ghost> ghosts = new ArrayList<>();
            ghosts.add(ghost);
            hunter.setGhosts(ghosts);
            
            em.persist(hunter);
            em.merge(ghost);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void markAsRetrieved(Long ghostId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ghost ghost = em.find(Ghost.class, ghostId);
            if (ghost == null) {
                throw new IllegalArgumentException("Ghost not found with ID: " + ghostId);
            }
            
            ghost.setStatus(Status.RETRIEVED);
            em.merge(ghost);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void markAsMissing(Long ghostId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ghost ghost = em.find(Ghost.class, ghostId);
            if (ghost == null) {
                throw new IllegalArgumentException("Ghost not found with ID: " + ghostId);
            }
            
            ghost.setStatus(Status.MISSING);
            em.merge(ghost);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
