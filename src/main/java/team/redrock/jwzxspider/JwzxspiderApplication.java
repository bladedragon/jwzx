package team.redrock.jwzxspider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JwzxspiderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwzxspiderApplication.class, args);
    }
}
