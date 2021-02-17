package ru.job4j.dream.store;

import ru.job4j.dream.model.*;

import java.util.Calendar;
import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    Collection<User> findAllUsers();

    Collection<City> findAllCities();

    Collection<Post> findAllPostsBetweenDates(Calendar from, Calendar to);

    Collection<Candidate> findAllCandidatesBetweenDates(Calendar from, Calendar to);

    void save(Post post);

    void save(Candidate candidate);

    void delete(Candidate candidate);

    void save(Photo photo);

    void save(User user);

    void delete(User user);

    Post findPostById(int id);

    Candidate findCandidateById(int id);

    User findUserById(int id);

    User findUserByEmail(String email);
}