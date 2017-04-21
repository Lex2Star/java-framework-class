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

    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }
}
