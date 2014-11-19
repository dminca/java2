/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.UserDB;
import model.AngajatDB;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.DepartamentDB;

/**
 *
 * @author student
 */
public class DepartamentDBJpaController implements Serializable {

    public DepartamentDBJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DepartamentDB departamentDB) {
        if (departamentDB.getAngajatDBCollection() == null) {
            departamentDB.setAngajatDBCollection(new ArrayList<AngajatDB>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserDB user = departamentDB.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getId());
                departamentDB.setUser(user);
            }
            Collection<AngajatDB> attachedAngajatDBCollection = new ArrayList<AngajatDB>();
            for (AngajatDB angajatDBCollectionAngajatDBToAttach : departamentDB.getAngajatDBCollection()) {
                angajatDBCollectionAngajatDBToAttach = em.getReference(angajatDBCollectionAngajatDBToAttach.getClass(), angajatDBCollectionAngajatDBToAttach.getId());
                attachedAngajatDBCollection.add(angajatDBCollectionAngajatDBToAttach);
            }
            departamentDB.setAngajatDBCollection(attachedAngajatDBCollection);
            em.persist(departamentDB);
            if (user != null) {
                user.getDepartamentDBCollection().add(departamentDB);
                user = em.merge(user);
            }
            for (AngajatDB angajatDBCollectionAngajatDB : departamentDB.getAngajatDBCollection()) {
                DepartamentDB oldDepartamentOfAngajatDBCollectionAngajatDB = angajatDBCollectionAngajatDB.getDepartament();
                angajatDBCollectionAngajatDB.setDepartament(departamentDB);
                angajatDBCollectionAngajatDB = em.merge(angajatDBCollectionAngajatDB);
                if (oldDepartamentOfAngajatDBCollectionAngajatDB != null) {
                    oldDepartamentOfAngajatDBCollectionAngajatDB.getAngajatDBCollection().remove(angajatDBCollectionAngajatDB);
                    oldDepartamentOfAngajatDBCollectionAngajatDB = em.merge(oldDepartamentOfAngajatDBCollectionAngajatDB);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DepartamentDB departamentDB) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DepartamentDB persistentDepartamentDB = em.find(DepartamentDB.class, departamentDB.getId());
            UserDB userOld = persistentDepartamentDB.getUser();
            UserDB userNew = departamentDB.getUser();
            Collection<AngajatDB> angajatDBCollectionOld = persistentDepartamentDB.getAngajatDBCollection();
            Collection<AngajatDB> angajatDBCollectionNew = departamentDB.getAngajatDBCollection();
            List<String> illegalOrphanMessages = null;
            for (AngajatDB angajatDBCollectionOldAngajatDB : angajatDBCollectionOld) {
                if (!angajatDBCollectionNew.contains(angajatDBCollectionOldAngajatDB)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AngajatDB " + angajatDBCollectionOldAngajatDB + " since its departament field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getId());
                departamentDB.setUser(userNew);
            }
            Collection<AngajatDB> attachedAngajatDBCollectionNew = new ArrayList<AngajatDB>();
            for (AngajatDB angajatDBCollectionNewAngajatDBToAttach : angajatDBCollectionNew) {
                angajatDBCollectionNewAngajatDBToAttach = em.getReference(angajatDBCollectionNewAngajatDBToAttach.getClass(), angajatDBCollectionNewAngajatDBToAttach.getId());
                attachedAngajatDBCollectionNew.add(angajatDBCollectionNewAngajatDBToAttach);
            }
            angajatDBCollectionNew = attachedAngajatDBCollectionNew;
            departamentDB.setAngajatDBCollection(angajatDBCollectionNew);
            departamentDB = em.merge(departamentDB);
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getDepartamentDBCollection().remove(departamentDB);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getDepartamentDBCollection().add(departamentDB);
                userNew = em.merge(userNew);
            }
            for (AngajatDB angajatDBCollectionNewAngajatDB : angajatDBCollectionNew) {
                if (!angajatDBCollectionOld.contains(angajatDBCollectionNewAngajatDB)) {
                    DepartamentDB oldDepartamentOfAngajatDBCollectionNewAngajatDB = angajatDBCollectionNewAngajatDB.getDepartament();
                    angajatDBCollectionNewAngajatDB.setDepartament(departamentDB);
                    angajatDBCollectionNewAngajatDB = em.merge(angajatDBCollectionNewAngajatDB);
                    if (oldDepartamentOfAngajatDBCollectionNewAngajatDB != null && !oldDepartamentOfAngajatDBCollectionNewAngajatDB.equals(departamentDB)) {
                        oldDepartamentOfAngajatDBCollectionNewAngajatDB.getAngajatDBCollection().remove(angajatDBCollectionNewAngajatDB);
                        oldDepartamentOfAngajatDBCollectionNewAngajatDB = em.merge(oldDepartamentOfAngajatDBCollectionNewAngajatDB);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamentDB.getId();
                if (findDepartamentDB(id) == null) {
                    throw new NonexistentEntityException("The departamentDB with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DepartamentDB departamentDB;
            try {
                departamentDB = em.getReference(DepartamentDB.class, id);
                departamentDB.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamentDB with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<AngajatDB> angajatDBCollectionOrphanCheck = departamentDB.getAngajatDBCollection();
            for (AngajatDB angajatDBCollectionOrphanCheckAngajatDB : angajatDBCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DepartamentDB (" + departamentDB + ") cannot be destroyed since the AngajatDB " + angajatDBCollectionOrphanCheckAngajatDB + " in its angajatDBCollection field has a non-nullable departament field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            UserDB user = departamentDB.getUser();
            if (user != null) {
                user.getDepartamentDBCollection().remove(departamentDB);
                user = em.merge(user);
            }
            em.remove(departamentDB);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DepartamentDB> findDepartamentDBEntities() {
        return findDepartamentDBEntities(true, -1, -1);
    }

    public List<DepartamentDB> findDepartamentDBEntities(int maxResults, int firstResult) {
        return findDepartamentDBEntities(false, maxResults, firstResult);
    }

    private List<DepartamentDB> findDepartamentDBEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DepartamentDB.class));
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

    public DepartamentDB findDepartamentDB(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DepartamentDB.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentDBCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DepartamentDB> rt = cq.from(DepartamentDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<DepartamentDB> getDepartmentsForUser(UserDB user){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("DepartamentDB.findByUser");
        q.setParameter("user", user);
        
        List<DepartamentDB> list = q.getResultList();
        return list;
    }
    
}
