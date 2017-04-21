package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM userinfo WHERE id = ?";
        Object[] params = {id};
        User user1 = null;

        try {
            user1 = jdbcTemplate.queryForObject(sql, params, (resultSet, i) -> {
                User user= new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            });
        } catch (DataAccessException e) {
//            e.printStackTrace();
        }

        return user1;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO userinfo(id, name, password) VALUES (?, ?, ?)";
        Object[] params = {user.getId(), user.getName(), user.getPassword()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM userinfo WHERE id = ?";
        Object[] params = {id};
        jdbcTemplate.update(sql, params);
    }
}
