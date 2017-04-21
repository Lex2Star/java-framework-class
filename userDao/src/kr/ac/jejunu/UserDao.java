package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public class UserDao {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public UserDao() { }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        return jdbcContext.jdbcContextWithStatementStrategyForGet(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }



    public void add(User user) throws SQLException, ClassNotFoundException {
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(id, name, password) VALUES (?, ?, ?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            return preparedStatement;
        });
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userinfo WHERE id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }


}
