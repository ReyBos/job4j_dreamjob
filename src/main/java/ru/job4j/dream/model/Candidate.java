package ru.job4j.dream.model;

import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private String photoName;
    private int cityId;
    private String cityName;

    public Candidate() {
        this.id = 0;
        this.name = "";
    }

    public Candidate(int id) {
        this.id = id;
        this.name = "";
    }

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candidate(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
    }

    public Candidate(int id, String name, String photoName) {
        this.id = id;
        this.name = name;
        this.photoName = photoName;
    }

    public Candidate(int id, String name, String photoName, int cityId, String cityName) {
        this.id = id;
        this.name = name;
        this.photoName = photoName;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + "}";
    }
}