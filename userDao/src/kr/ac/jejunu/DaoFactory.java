package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hyunki on 2017. 4. 21..
 */
@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(getConnection());
    }
    @Bean
    public ConnectionMaker getConnection() {
        return new JejuUserDao();
    }
}
