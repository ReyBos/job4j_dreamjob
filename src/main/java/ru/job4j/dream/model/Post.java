package ru.job4j.dream.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String description;
    private Calendar created;

    public Post(int id, String name, String description, Calendar created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getCreated() {
        return created;
    }

    public String getFormattedCreated() {
        DateFormat df = new SimpleDateFormat("dd MMM yyy");
        return df.format(created.getTime());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}