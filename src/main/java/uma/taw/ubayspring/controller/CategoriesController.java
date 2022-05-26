package uma.taw.ubayspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.taw.ubayspring.dto.categories.AddCategoryDTO;
import uma.taw.ubayspring.dto.categories.CategoriesDTO;
import uma.taw.ubayspring.dto.categories.CategoryDTO;
import uma.taw.ubayspring.dto.categories.ModifyCategoryDTO;
import uma.taw.ubayspring.exception.UbayException;
import uma.taw.ubayspring.service.CategoriesService;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping()
    public String categories(Model model){
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        CategoriesDTO categoriesDTO = categoriesService.processCategories(user);

        model.addAttribute("user-fav-category-list", categoriesDTO.getUserFavouriteCategories());
        model.addAttribute("category-list", categoriesDTO.getCategoryList());
        model.addAttribute("client-id", categoriesDTO.getUserID());
        model.addAttribute("login", categoriesDTO.getLogin());

        return "categories/categories";
    }

    @GetMapping("/add")
    public AddCategoryDTO getAdd(@ModelAttribute AddCategoryDTO addCategoryDTO){
        return addCategoryDTO;
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute AddCategoryDTO addCategoryDTO){
        categoriesService.addCategory(addCategoryDTO.getName(), addCategoryDTO.getDescription());
        return "redirect:";
    }

    @GetMapping("/addFavourite")
    public String addFavourite(@RequestParam String clientID, @RequestParam String categoryID){
        categoriesService.addFavouriteCategory(clientID, categoryID);
        return "redirect:";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) throws UbayException {
        categoriesService.deleteCategory(id);

        return "redirect:";
    }

    @GetMapping("/deleteFavourite")
    public String deleteFavourite(@RequestParam String clientID, @RequestParam String categoryID){
        categoriesService.deleteFavourite(clientID, categoryID);

        return "redirect:";
    }

    @GetMapping("/modify")
    public CategoryDTO getModify(@ModelAttribute CategoryDTO categoryDTO){
        return categoryDTO;
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute CategoryDTO categoryDTO){
        categoriesService.modify(categoryDTO);
        return "redirect:";
    }
}
