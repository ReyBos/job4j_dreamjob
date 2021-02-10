package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class PsqlStore implements Store {
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (InputStream in = PsqlStore.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            cfg.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        Post searchedPost = new Post();
        String sql = "select * from post";
        BiConsumerException<PreparedStatement, Post> setValues = (statement, post) -> { };
        return searchPost(searchedPost, sql, setValues);
    }

    @Override
    public Post findPostById(int id) {
        Post searchedPost = new Post(id);
        String sql = "select * from post where id = ?";
        BiConsumerException<PreparedStatement, Post> setValues = (statement, post) -> {
            statement.setInt(1, post.getId());
        };
        List<Post> rsl = searchPost(searchedPost, sql, setValues);
        return rsl.size() == 0 ? null : rsl.get(0);
    }

    private List<Post> searchPost(
            Post post, String sql, BiConsumerException<PreparedStatement, Post> setValues
    ) {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            setValues.accept(ps, post);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Calendar date = new GregorianCalendar();
                    date.setTime(resultSet.getDate("created"));
                    posts.add(new Post(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            date
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        Candidate searchedCandidate = new Candidate();
        String sql = "select * from candidate";
        BiConsumerException<PreparedStatement, Candidate> setValues = (statement, candidate) -> { };
        return searchCandidate(searchedCandidate, sql, setValues);
    }

    @Override
    public Candidate findCandidateById(int id) {
        Candidate searchedCandidate = new Candidate(id);
        String sql = "select * from candidate where id = ?";
        BiConsumerException<PreparedStatement, Candidate> setValues = (statement, candidate) -> {
            statement.setInt(1, candidate.getId());
        };
        List<Candidate> rsl = searchCandidate(searchedCandidate, sql, setValues);
        return rsl.size() == 0 ? null : rsl.get(0);
    }

    private List<Candidate> searchCandidate(
            Candidate candidate,
            String sql,
            BiConsumerException<PreparedStatement, Candidate> setValues
    ) {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            setValues.accept(ps, candidate);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    candidates.add(new Candidate(
                            resultSet.getInt("id"), resultSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            create(post);
        } else {
            update(post);
        }
    }

    private void create(Post post) {
        String sql = "insert into post(name, description, created) values (?, ?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS
             )
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, new Timestamp(post.getCreated().getTimeInMillis()));
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(Post post) {
        String sql = "update post set name = ?, description = ?, created = ? where id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, new Timestamp(post.getCreated().getTimeInMillis()));
            ps.setInt(4, post.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            create(candidate);
        } else {
            update(candidate);
        }
    }

    private void create(Candidate candidate) {
        String sql = "insert into candidate(name) values (?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS
             )
        ) {
            ps.setString(1, candidate.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(Candidate candidate) {
        String sql = "update candidate set name = ? where id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}