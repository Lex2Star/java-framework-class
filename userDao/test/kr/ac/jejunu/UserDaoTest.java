package kr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public class UserDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "이현기";
        String password = "1111";

        UserDao userDao = new UserDao();
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "lex";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new UserDao();
        Long id = userDao.add(user);

        User user1 = userDao.get(id);
        assertThat(id, is(user1.getId()));
        assertThat(name, is(user1.getName()));
        assertThat(password, is(user1.getPassword()));
    }
}
