/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n2.jpa;

import n2.dao.exceptions.NonexistentEntityException;
import n2.dao.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import n2.modelo.TipoImovel;
import java.sql.SQLException;

/**
 *
 * @author aluno
 */
public class TipoImovelJpaController implements IDAO {

    public TipoImovelJpaController() {
        this.emf = Persistence.createEntityManagerFactory("N2PU");
    }
    private EntityTransaction ent = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoImovel tipoImovel) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            ent = em.getTransaction();
            ent.begin();
            em.persist(tipoImovel);
            ent.commit();
        } catch (Exception ex) {
            try {
                ent.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoImovel tipoImovel) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            ent = em.getTransaction();
            ent.begin();
            tipoImovel = em.merge(tipoImovel);
            ent.commit();
        } catch (Exception ex) {
            try {
                ent.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoImovel.getId();
                if (findTipoImovel(id) == null) {
                    throw new NonexistentEntityException("The tipoImovel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            ent = em.getTransaction();
            ent.begin();
            TipoImovel tipoImovel;
            try {
                tipoImovel = em.getReference(TipoImovel.class, id);
                tipoImovel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoImovel with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoImovel);
            ent.commit();
        } catch (Exception ex) {
            try {
                ent.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoImovel> findTipoImovelEntities() {
        return findTipoImovelEntities(true, -1, -1);
    }

    public List<TipoImovel> findTipoImovelEntities(int maxResults, int firstResult) {
        return findTipoImovelEntities(false, maxResults, firstResult);
    }

    private List<TipoImovel> findTipoImovelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoImovel.class));
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

    public TipoImovel findTipoImovel(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoImovel.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoImovelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoImovel> rt = cq.from(TipoImovel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public void cadastrar(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List listarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object listarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
