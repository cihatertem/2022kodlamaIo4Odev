package kodlama.io.devs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevsApplication.class, args);
    }

}
