package ru.edu.space.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author agerus
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableJpaRepositories(basePackages = "ru.edu.space.app.*")
@EntityScan("ru.edu.space.app.*")
@ComponentScan({
        "ru.edu.space.app.*"
})
public class EduApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(EduApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(-1);
        }
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
