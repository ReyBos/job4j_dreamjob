package ru.job4j.dream.model;

public class Photo {
    private int id;
    private String name;
    private int candidateId;

    public Photo(int id) {
        this.id = id;
    }

    public Photo(String name, int candidateId) {
        this.id = 0;
        this.name = name;
        this.candidateId = candidateId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCandidateId() {
        return candidateId;
    }
}
