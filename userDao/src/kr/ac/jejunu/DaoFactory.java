package kr.ac.jejunu;

/**
 * Created by hyunki on 2017. 4. 14..
 */
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    private ConnectionMaker getConnectionMaker() {
        return new JejuUserDao();
    }
}
