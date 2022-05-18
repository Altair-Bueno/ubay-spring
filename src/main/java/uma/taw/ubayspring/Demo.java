package uma.taw.ubayspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Demo {

    @GetMapping("/demo")
    public String doGet(Model model){
        return "demo";
    }
}
