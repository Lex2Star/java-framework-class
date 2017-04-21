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

    public UserDao() { }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = {id};
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, params, (resultSet, i) -> {
                User user1 = new User();
                user1.setId(resultSet.getLong("id"));
                user1.setName(resultSet.getString("name"));
                user1.setPassword(resultSet.getString("password"));
                return user1;
            });
        } catch (DataAccessException e) {
//            e.printStackTrace();
        }
        return user;
    }



    public void add(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO userinfo(id, name, password) VALUES (?, ?, ?)";
        Object[] params = {user.getId(), user.getName(), user.getPassword()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM userinfo WHERE id = ?";
        Object[] params = {id};
        jdbcTemplate.update(sql, params);
    }


}
