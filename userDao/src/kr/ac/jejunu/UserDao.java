package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public class UserDao {

    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO userinfo(id, name, password) VALUES (?, ?, ?)";
        Object[] params = {user.getId(), user.getName(), user.getPassword()};
        jdbcContext.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM userinfo WHERE id = ?";
        Object[] params = {id};
        jdbcContext.update(sql, params);
    }
}
