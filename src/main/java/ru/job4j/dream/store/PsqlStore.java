package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.*;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PsqlStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());
    private final BasicDataSource pool = new BasicDataSource();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    public Collection<Post> findAllPostsBetweenDates(Calendar from, Calendar to) {
        Post searchedPost = new Post();
        String fromDate = dateFormat.format(from.getTime());
        String toDate = dateFormat.format(to.getTime());
        String sql = "select * from post where created BETWEEN '"
                + fromDate + "' AND '" + toDate + "';";
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
            LOG.error("Error", e);
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        Candidate searchedCandidate = new Candidate();
        String sql = "SELECT candidate.*, photo.id AS photo_name, city.name AS city_name "
                + " FROM candidate "
                + " LEFT JOIN photo ON candidate.id = photo.candidate_id "
                + " LEFT JOIN city ON candidate.city_id = city.id";
        BiConsumerException<PreparedStatement, Candidate> setValues = (statement, candidate) -> { };
        return searchCandidate(searchedCandidate, sql, setValues);
    }

    @Override
    public Collection<Candidate> findAllCandidatesBetweenDates(Calendar from, Calendar to) {
        Candidate searchedCandidate = new Candidate();
        String fromDate = dateFormat.format(from.getTime());
        String toDate = dateFormat.format(to.getTime());
        String sql = "SELECT candidate.*, photo.id AS photo_name, city.name AS city_name "
                + " FROM candidate "
                + " LEFT JOIN photo ON candidate.id = photo.candidate_id "
                + " LEFT JOIN city ON candidate.city_id = city.id"
                + " WHERE created BETWEEN '" + fromDate + "' AND '" + toDate + "';";
        BiConsumerException<PreparedStatement, Candidate> setValues = (statement, candidate) -> { };
        return searchCandidate(searchedCandidate, sql, setValues);
    }

    @Override
    public Candidate findCandidateById(int id) {
        Candidate searchedCandidate = new Candidate(id);
        String sql = "SELECT candidate.*, photo.id AS photo_name, city.name AS city_name "
                + " FROM candidate "
                + " LEFT JOIN photo ON candidate.id = photo.candidate_id "
                + " LEFT JOIN city ON candidate.city_id = city.id"
                + " WHERE candidate.id = ?";
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
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("photo_name"),
                            resultSet.getInt("city_id"),
                            resultSet.getString("city_name")
                    ));
                }
            }
        } catch (SQLException e) {
            LOG.error("Error", e);
        }
        return candidates;
    }

    @Override
    public Collection<User> findAllUsers() {
        User searchedUser = new User();
        String sql = "select * from users";
        BiConsumerException<PreparedStatement, User> setValues = (statement, user) -> { };
        return searchUser(searchedUser, sql, setValues);
    }

    @Override
    public User findUserById(int id) {
        User searchedUser = new User(id);
        String sql = "select * from users where id = ?";
        BiConsumerException<PreparedStatement, User> setValues = (statement, user) -> {
            statement.setInt(1, user.getId());
        };
        List<User> rsl = searchUser(searchedUser, sql, setValues);
        return rsl.size() == 0 ? null : rsl.get(0);
    }

    @Override
    public User findUserByEmail(String email) {
        User searchedUser = new User(email);
        String sql = "select * from users where email = ?";
        BiConsumerException<PreparedStatement, User> setValues = (statement, user) -> {
            statement.setString(1, user.getEmail());
        };
        List<User> rsl = searchUser(searchedUser, sql, setValues);
        return rsl.size() == 0 ? null : rsl.get(0);
    }

    private List<User> searchUser(
            User user, String sql, BiConsumerException<PreparedStatement, User> setValues
    ) {
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            setValues.accept(ps, user);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    ));
                }
            }
        } catch (SQLException e) {
            LOG.error("Error", e);
        }
        return users;
    }

    @Override
    public Collection<City> findAllCities() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM city;";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            LOG.error("Error", e);
        }
        return cities;
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
            LOG.error("Error", e);
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
            LOG.error("Error", e);
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
        String sql = "insert into candidate(name, city_id) values (?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS
             )
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getCityId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    private void update(Candidate candidate) {
        String sql = "update candidate set name = ?, city_id = ? where id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getCityId());
            ps.setInt(3, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    @Override
    public void delete(Candidate candidate) {
        if (candidate.getId() == 0) {
            return;
        }
        String sql = "delete from candidate where id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            ps.setInt(1, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    @Override
    public void save(Photo photo) {
        String sql = "insert into photo(name, candidate_id) values (?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS
             )
        ) {
            ps.setString(1, photo.getName());
            ps.setInt(2, photo.getCandidateId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    photo.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            create(user);
        } else {
            update(user);
        }
    }

    private void create(User user) {
        String sql = "insert into users(name, email, password) values (?, ?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS
             )
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    private void update(User user) {
        String sql = "update users set name = ?, email = ?, password = ? where id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getName());
            ps.setString(3, user.getName());
            ps.setInt(4, user.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }

    @Override
    public void delete(User user) {
        if (user.getId() == 0) {
            return;
        }
        String sql = "delete from users where id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)
        ) {
            ps.setInt(1, user.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }
}