package bouldercow.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "bouldercow")
@EnableScheduling
public class BouldercowApp {
    public static void main(String[] args) {
        SpringApplication.run(BouldercowApp.class, args);
    }
}
