package uma.taw.ubayspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Altair Bueno
 */
@SpringBootApplication
@Controller
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("")
    public String getIndex(){
        return "redirect:/product";
    }

    @GetMapping("/")
    public String dev() {
        return "redirect:/product";
    }
}
