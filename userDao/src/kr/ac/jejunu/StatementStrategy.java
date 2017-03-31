package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by hyunki on 2017. 3. 31..
 */
public interface StatementStrategy {
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException;
}
