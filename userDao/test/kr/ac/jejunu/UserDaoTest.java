package kr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Random;

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

        UserDao userDao = new UserDao(new JejuConnection());
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        Long id = Long.valueOf(new Random().nextInt());
        String name = "lex";
        String password = "2222";

        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new UserDao(new JejuConnection());
        userDao.add(user);
        User user1 = userDao.get(id);

        assertThat(user1.getId(), is(id));
        assertThat(user1.getName(), is(name));
        assertThat(user1.getPassword(), is(password));
    }
}
