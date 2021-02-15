package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    Collection<User> findAllUsers();

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