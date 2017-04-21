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
        return new UserDao(getConnetion());
    }

    @Bean
    public ConnectionMaker getConnetion() {
        return new JejuConnection();
    }
}
