package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.GregorianCalendar;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        Post post1 = new Post(0, "Java Job", "Description", new GregorianCalendar());
        Post post2 = new Post(0, "Java Job Middle", "Description", new GregorianCalendar());
        store.save(post1);
        store.save(post2);
        for (Post post : store.findAllPosts()) {
            System.out.println(post);
        }
        System.out.println("-----------------");
        post1.setName("Измененное имя");
        store.save(post1);
        System.out.println(store.findPostById(post1.getId()));
        System.out.println("-----------------");
        Candidate candidate1 = new Candidate(0, "Andrew");
        Candidate candidate2 = new Candidate(0, "Tom");
        store.save(candidate1);
        store.save(candidate2);
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate);
        }
        System.out.println("-----------------");
        candidate1.setName("Artem");
        store.save(candidate1);
        System.out.println(store.findCandidateById(candidate1.getId()));
        System.out.println("-----------------");
        User user = new User(0, "Andrew", "email", "12345");
        store.save(user);
        System.out.println(store.findUserById(user.getId()));
        System.out.println(store.findUserByEmail(user.getEmail()));
        for (User item : store.findAllUsers()) {
            System.out.println(item);
        }
        store.delete(user);
    }
}
