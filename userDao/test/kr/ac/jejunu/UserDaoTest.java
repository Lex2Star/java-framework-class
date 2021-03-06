package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by hyunki on 2017. 3. 15..
 */
public class UserDaoTest {
    UserDao userDao;

    @Before
    public void setup() {
//        daoFactory = new DaoFactory();
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "이현기";
        String password = "1111";

        User user = userDao.get(id);

        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        String name = "lex2star";
        String password = "1111";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        User resultUser = userDao.get(id);

        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        String name = "lex2star";
        String password = "1111";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        String changedName = "sssss";
        String changedPassword = "aaaaa";

        user.setId(id);
        user.setName(changedName);
        user.setPassword(changedPassword);

        userDao.update(user);

        User changedUser = userDao.get(id);

        assertThat(changedName, is(changedUser.getName()));
        assertThat(changedPassword, is(changedUser.getPassword()));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        String name = "lex2star";
        String password = "1111";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        userDao.delete(id);

        User deletedUser = userDao.get(id);

        assertThat(deletedUser, nullValue());
    }
}
