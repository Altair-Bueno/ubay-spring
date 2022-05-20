package uma.taw.ubayspring.controller.users;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.taw.ubayspring.dto.users.ClientDTO;
import uma.taw.ubayspring.service.UsersService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Users {

    @Autowired
    UsersService usersService;

    @GetMapping("/users")
    public String client(Model model,
                         @RequestParam (defaultValue = "") String id ,
                         @RequestParam (defaultValue = "") String name,
                         @RequestParam (defaultValue = "") String lastName,
                         @RequestParam (defaultValue = "") String address,
                         @RequestParam (defaultValue = "") String city,
                         @RequestParam (defaultValue = "") String gender){

        List<ClientDTO> clientDTOList = usersService.users(id, name, lastName, address, city, gender);

        model.addAttribute("search-user", clientDTOList);
        return "users/users";
    }

    @GetMapping("/users/delete")
    public String delete(@RequestParam String id){
        usersService.deleteUser(id);
        return "users/delete";
    }
}
