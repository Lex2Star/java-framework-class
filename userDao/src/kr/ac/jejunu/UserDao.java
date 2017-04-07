package kr.ac.jejunu;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public class UserDao {

    private DataSource dataSource;

//    public void setConnectionMaker(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try{
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new GetUserStatement(id);
            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
//            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;

        try {
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new AddUserStatement(user);
            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement("insert into userinfo(name, password) VALUES (?,?)");
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        }  catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return id;
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new UpdateUserStatement(user);
            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement("update userinfo set name = ?, password = ? where id = ?");
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void delete(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new DeleteUserStatement(id);
            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
//            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
