package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public abstract class UserDao {

    public User get(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

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

    public void add(User user) throws SQLException, ClassNotFoundException {
     Connection connection = getConnection();

     PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(id, name, password) VALUES (?, ?, ?)");
     preparedStatement.setLong(1, user.getId());
     preparedStatement.setString(2, user.getName());
     preparedStatement.setString(3, user.getPassword());
     preparedStatement.executeUpdate();

     preparedStatement.close();
     connection.close();
    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
