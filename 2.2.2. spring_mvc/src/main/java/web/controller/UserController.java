package web.controller;

import hiber.model.Role;
import hiber.model.User;
import hiber.service.RoleService;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String homePage() {
        return "admin/users";
    }

    @GetMapping("/admin/users")
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "admin/users";
    }

    @GetMapping("/login")
    public String loginPage(ModelMap model) {
        return "/login";
    }

    @GetMapping("/admin/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute(userService.getUser(id));
        return "admin/show";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        Role r = new Role();
        User user = new User();
        List<Role> showRoles = roleService.listRoles();
        model.addAttribute("r", r);
        model.addAttribute("user", user);
        model.addAttribute("showRoles", showRoles);
        return "admin/new";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        Role r = new Role();
        model.addAttribute("r", r);
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("showRoles", roleService.listRoles());
        return "admin/edit";
    }

    @GetMapping("/user/userspace/{id}")
    public String userspace(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user/userspace";
    }

    @GetMapping("/admin/adminspace/{id}")
    public String adminspace(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "/admin/adminspace";
    }

    @PostMapping("/admin/new")
    public String createUser(Model model, @ModelAttribute("user") User user, @ModelAttribute("r") Role role) {
        Role roleRole = roleService.listRoles().stream().filter(x -> x.getId() == role.getId()).findAny().get();
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setRole(roleRole);
        userService.add(u);
        model.addAttribute("users", userService.listUsers());
        return "admin/users";
    }

    @PatchMapping("/admin/users/{id}")
    public String updateUser(Model model, @ModelAttribute("user") User user, @ModelAttribute("r") Role role, @PathVariable("id") long id) {
        Role newRole = roleService.listRoles().stream().filter(x -> x.getName().equals(role.getName())).findAny().get();
        User u = userService.getUser(id);
        u.setRole(newRole);
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setUsername(user.getUsername());
        userService.updateUser(u);
        model.addAttribute("users", userService.listUsers());
        return "admin/users";
    }

    @DeleteMapping("/admin/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") long id) {
        userService.deleteUser(userService.getUser(id));
        model.addAttribute("users", userService.listUsers());
        return "admin/users";
    }
}
