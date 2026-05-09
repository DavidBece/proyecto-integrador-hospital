package com.proyectohospital.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

import com.proyectohospital.dao.interfaces.GenericDAO;
import com.proyectohospital.utils.JPAUtil;

public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
    private final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager entityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public T findById(ID id) {
        EntityManager em = entityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> findAll() {
        EntityManager em = entityManager();
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void save(T entity) {
        executeInTransaction(em -> em.persist(entity));
    }

    @Override
    public T update(T entity) {
        final Object[] result = new Object[1];
        executeInTransaction(em -> result[0] = em.merge(entity));
        return entityClass.cast(result[0]);
    }

    @Override
    public void delete(T entity) {
        executeInTransaction(em -> {
            T managed = em.contains(entity) ? entity : em.merge(entity);
            em.remove(managed);
        });
    }

    protected void executeInTransaction(EntityManagerWork work) {
        EntityManager em = entityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            work.execute(em);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @FunctionalInterface
    protected interface EntityManagerWork {
        void execute(EntityManager em);
    }
}
