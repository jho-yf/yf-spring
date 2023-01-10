package cn.jho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>SecurityQuickStartApp class.</p>
 *
 * @author JHO xu-jihong@qq.com
 */
@SpringBootApplication
@RestController
public class SecurityQuickStartApp {

    public static void main(String[] args) {
        SpringApplication.run(SecurityQuickStartApp.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "<h1>hello SecurityQuickStartApp.</h1>";
    }

}
