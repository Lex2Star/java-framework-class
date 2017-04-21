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

    public UserDao() { }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new StatementStrategyForGet(id);
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }



    public void add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new StatementStrategyForAdd(user);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new StatementStrategyForDelete(id);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }


}
