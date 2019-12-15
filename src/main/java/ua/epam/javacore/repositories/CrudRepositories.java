package ua.epam.javacore.repositories;

import java.util.List;

public interface CrudRepositories<T> {

    T getById(Long id);
    List<T> getAll();
    void save(T obj);
    T deleteById(Long id);
    boolean update(T obj);

}
