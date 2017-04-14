package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public class UserDao {
//    private ConnectionMaker connectionMaker;

//    public UserDao(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();

        StatementStrategy statementStrategy = new GetUserStatement();
        PreparedStatement preparedStatement = statementStrategy.makeStatement(id, connection);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }


    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();

        StatementStrategy statementStrategy = new AddUserStatement();
        PreparedStatement preparedStatement = statementStrategy.makeStatement(user, connection);
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }
}
