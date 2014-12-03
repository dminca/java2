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
import server.db.ProdusDB;

/**
 *
 * @author student
 */
public class ProdusDBJpaController implements Serializable {

    public ProdusDBJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProdusDB produsDB) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(produsDB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProdusDB produsDB) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            produsDB = em.merge(produsDB);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produsDB.getId();
                if (findProdusDB(id) == null) {
                    throw new NonexistentEntityException("The produsDB with id " + id + " no longer exists.");
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
            ProdusDB produsDB;
            try {
                produsDB = em.getReference(ProdusDB.class, id);
                produsDB.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produsDB with id " + id + " no longer exists.", enfe);
            }
            em.remove(produsDB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProdusDB> findProdusDBEntities() {
        return findProdusDBEntities(true, -1, -1);
    }

    public List<ProdusDB> findProdusDBEntities(int maxResults, int firstResult) {
        return findProdusDBEntities(false, maxResults, firstResult);
    }

    private List<ProdusDB> findProdusDBEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProdusDB.class));
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

    public ProdusDB findProdusDB(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProdusDB.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdusDBCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProdusDB> rt = cq.from(ProdusDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
