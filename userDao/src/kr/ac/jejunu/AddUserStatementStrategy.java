package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by hyunki on 2017. 4. 21..
 */
public class AddUserStatementStrategy implements StatementStrategy {

    @Override
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
        User user = (User)object;
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(id, name, password) values (?, ?, ?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        return preparedStatement;
    }
}
