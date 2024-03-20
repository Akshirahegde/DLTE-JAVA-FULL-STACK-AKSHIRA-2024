package org.example;

public interface Activity<T> {
    T create(T object);

    T read(int id);

    void update(int id, T updateObject);

    void delete(int id);

}
