package learning;

import learning.aop.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lihaodi
 */
@SpringBootApplication
public class LearningApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }

    @Autowired
    private UserServiceImpl userService;


    @Override
    public void run(String... args) throws Exception {
        userService.saveUser();
        userService.save();
    }
}
