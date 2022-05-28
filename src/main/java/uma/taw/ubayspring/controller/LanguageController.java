package uma.taw.ubayspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping("/language")
public class LanguageController {
    @GetMapping
    public String changeLanguage(@RequestParam String code, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String referer = request.getHeader("referer");
        String refererbase = referer.split("\\?")[0];
        String attributes="?";
        if(code.equals("en") || code.equals("es")){
            if(referer.contains("lang")){
                String[] refererAttributes = referer.split("\\?")[1].split("&");
                for(int i = 0; i < refererAttributes.length; i++){
                    if(refererAttributes[i].contains("lang")){
                        refererAttributes[i] = "lang=" + code;
                    }
                    attributes+=refererAttributes[i];
                }
            } else {
                redirectAttributes.addAttribute("lang", code);
            }

        }
        return "redirect:" + refererbase + attributes;
    }
}
