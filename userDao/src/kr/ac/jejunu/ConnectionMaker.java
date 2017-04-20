package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by hyunki on 2017. 4. 20..
 */
public interface ConnectionMaker {
    Connection getConnection() throws SQLException, ClassNotFoundException;
}
