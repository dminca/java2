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
import server.db.UserDB;

/**
 *
 * @author student
 */
public class UserDBJpaController implements Serializable {

    public UserDBJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserDB userDB) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userDB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserDB userDB) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userDB = em.merge(userDB);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userDB.getId();
                if (findUserDB(id) == null) {
                    throw new NonexistentEntityException("The userDB with id " + id + " no longer exists.");
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
            UserDB userDB;
            try {
                userDB = em.getReference(UserDB.class, id);
                userDB.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userDB with id " + id + " no longer exists.", enfe);
            }
            em.remove(userDB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserDB> findUserDBEntities() {
        return findUserDBEntities(true, -1, -1);
    }

    public List<UserDB> findUserDBEntities(int maxResults, int firstResult) {
        return findUserDBEntities(false, maxResults, firstResult);
    }

    private List<UserDB> findUserDBEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserDB.class));
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

    public UserDB findUserDB(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserDB.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserDBCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserDB> rt = cq.from(UserDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public UserDB getUserByUsername(String username){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("UserDB.findByUsername");
        q.setParameter("username", username);
        
        try{
            return (UserDB) q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    
}
