package ru.job4j.dream.model;

import java.io.Serializable;

public class City implements Serializable {
    private int id;
    private String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
