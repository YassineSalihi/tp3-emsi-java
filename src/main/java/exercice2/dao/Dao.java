package exercice2.dao;

import java.util.List;

public interface Dao<T> {
    void insert(T obj);
    List<T> findAll();
    // To add later : update(T obj), delete(int id), findById(int id)
}
