package uma.taw.ubayspring.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.repository.ClientRepository;

import java.util.List;

@Controller
public class Users {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/")
    public String client(Model model){
        //Page<ClientEntity> pagesClient = clientRepository.findAll(new Pageable());
        //List<ClientEntity> clientList =  pagesClient.stream().toList();

        //model.addAttribute("clientList", clientList);
        return "users/users";
    }
}
