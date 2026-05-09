package com.proyectohospital.dao.interfaces;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id);
    List<T> findAll();
    void save(T entity);
    T update(T entity);
    void delete(T entity);
}
