package ru.job4j.dream.store;

import java.sql.SQLException;

@FunctionalInterface
public interface BiConsumerException<T, U> {
    void accept(T t, U u) throws SQLException;
}