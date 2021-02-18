package ru.job4j.dream.store;

import ru.job4j.dream.model.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {
    private static final MemStore INST = new MemStore();
    private final static AtomicInteger POST_ID = new AtomicInteger(4);
    private final static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    private final static AtomicInteger PHOTO_ID = new AtomicInteger(0);
    private final static AtomicInteger USER_ID = new AtomicInteger(0);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final Map<Integer, Photo> photos = new ConcurrentHashMap<>();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private MemStore() {
        posts.put(1, new Post(
                1, "Junior Java Job", "Работа для джуниоров", new GregorianCalendar()
                ));
        posts.put(2, new Post(
                2, "Middle Java Job", "Работа для мидлов", new GregorianCalendar()
        ));
        posts.put(3, new Post(
                3, "Senior Java Job", "Работа для сеньоров", new GregorianCalendar()
        ));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }

    public static MemStore instOf() {
        return INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    @Override
    public Collection<User> findAllUsers() {
        return users.values();
    }

    @Override
    public Collection<City> findAllCities() {
        return null;
    }

    @Override
    public Collection<Post> findAllPostsBetweenDates(Calendar from, Calendar to) {
        return null;
    }

    @Override
    public Collection<Candidate> findAllCandidatesBetweenDates(Calendar from, Calendar to) {
        return null;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    @Override
    public void delete(Candidate candidate) {
        candidates.remove(candidate.getId());
    }

    @Override
    public void save(Photo photo) {
        if (photo.getId() == 0) {
            photo.setId(PHOTO_ID.incrementAndGet());
        }
        photos.put(photo.getId(), photo);
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId());
    }

    @Override
    public void delete(Post post) {
        posts.remove(post.getId());
    }

    @Override
    public void delete(Photo photo) {

    }

    @Override
    public Post findPostById(int id) {
        return posts.get(id);
    }

    @Override
    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    @Override
    public User findUserById(int id) {
        return users.get(id);
    }

    @Override
    public User findUserByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}