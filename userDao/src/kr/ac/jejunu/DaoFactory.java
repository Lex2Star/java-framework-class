package kr.ac.jejunu;

/**
 * Created by hyunki on 2017. 4. 21..
 */
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnetion());
    }

    public ConnectionMaker getConnetion() {
        return new JejuConnection();
    }
}
