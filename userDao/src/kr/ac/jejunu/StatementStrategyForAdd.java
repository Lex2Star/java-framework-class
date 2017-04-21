package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by hyunki on 2017. 4. 21..
 */
public class StatementStrategyForAdd implements StatementStrategy {
    private User user;

    public StatementStrategyForAdd(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(id, name, password) VALUES (?, ?, ?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        return preparedStatement;
    }
}
