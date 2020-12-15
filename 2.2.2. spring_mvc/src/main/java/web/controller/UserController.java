package web.controller;

import hiber.model.Role;
import hiber.model.User;
import hiber.service.RoleService;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String homePage(){
        return "/users";
    }

    @GetMapping("/users")
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "/users";
    }

    @GetMapping("/login")
    public String loginPage(ModelMap model) {
        return "/login";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute(userService.getUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        Role r = new Role();
        User user = new User();
        List<Role> showRoles = roleService.listRoles();
        model.addAttribute("r", r);
        model.addAttribute("user", user);
        model.addAttribute("showRoles", showRoles);
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @GetMapping("/userspace")
    public String userspace(){
        return "/userspace";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user, @ModelAttribute("r") Role role) {
        Role roleRole = roleService.listRoles().stream().filter(x -> x.getId() == role.getId()).findAny().get();
        user.setRole(roleRole);
        userService.add(user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(userService.getUser(id));
        return "redirect:/users";
    }
}
