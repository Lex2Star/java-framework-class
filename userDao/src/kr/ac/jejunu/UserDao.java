package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public class UserDao {

//    private ConnectionMaker connectionMaker;
    private DataSource dataSource;

    public UserDao() { }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    public void setConnectionMaker(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
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

//    public Long add(User user) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju?characterEncoding=utf-8", "jeju", "jejupw");
//
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name, password) values (?, ?)");
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getPassword());
//        preparedStatement.executeUpdate();
//
//        preparedStatement = connection.prepareStatement("select last_insert_id()");
//        ResultSet resultSet = preparedStatement.executeQuery();
//        resultSet.next();
//        Long id = resultSet.getLong(1);
//
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();
//
//        return id;
//    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(id, name, password) values (?, ?, ?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
}
