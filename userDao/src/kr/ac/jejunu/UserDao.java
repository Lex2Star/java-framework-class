package kr.ac.jejunu;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
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
        StatementStrategy statementStrategy = new GetUserStatement(id);
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddUserStatement(user);
        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStrategy);
    }


    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new UpdateUserStatement(user);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }


    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatement(id);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }
}
