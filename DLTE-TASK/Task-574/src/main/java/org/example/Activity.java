package org.example;

import java.io.IOException;

public interface Activity<T> {
    void create(T object) throws IOException;
}
