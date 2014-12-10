/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import server.controllers.exceptions.NonexistentEntityException;
import server.db.PersoanaDB;

/**
 *
 * @author student
 */
public class PersoanaDBJpaController implements Serializable {

    public PersoanaDBJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersoanaDB persoanaDB) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(persoanaDB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersoanaDB persoanaDB) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            persoanaDB = em.merge(persoanaDB);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persoanaDB.getId();
                if (findPersoanaDB(id) == null) {
                    throw new NonexistentEntityException("The persoanaDB with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PersoanaDB persoanaDB;
            try {
                persoanaDB = em.getReference(PersoanaDB.class, id);
                persoanaDB.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persoanaDB with id " + id + " no longer exists.", enfe);
            }
            em.remove(persoanaDB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersoanaDB> findPersoanaDBEntities() {
        return findPersoanaDBEntities(true, -1, -1);
    }

    public List<PersoanaDB> findPersoanaDBEntities(int maxResults, int firstResult) {
        return findPersoanaDBEntities(false, maxResults, firstResult);
    }

    private List<PersoanaDB> findPersoanaDBEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersoanaDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PersoanaDB findPersoanaDB(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersoanaDB.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersoanaDBCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersoanaDB> rt = cq.from(PersoanaDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
