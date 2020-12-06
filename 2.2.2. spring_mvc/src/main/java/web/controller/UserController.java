package web.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

//@Autowired
//UserService userService;



    @GetMapping(value = "/users")
    public String printWelcome(ModelMap model) {
        //List<User> users = userService.listUsers();
        List<User> users = new ArrayList<>();
        users.add(new User("asdsad","sadsad","sadasd"));
        model.addAttribute("users", users);
        return "users";
    }
}
