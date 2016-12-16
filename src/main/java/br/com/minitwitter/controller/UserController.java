package br.com.minitwitter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.minitwitter.model.Role;
import br.com.minitwitter.model.User;
import br.com.minitwitter.service.RoleService;
import br.com.minitwitter.service.UserService;

@Controller
public class UserController {
  
  private UserService userService;
  private RoleService roleService;
  
  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  
  @Autowired
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping("/user")
  public String createUser(@Valid @ModelAttribute User user, BindingResult br) {
    if (userService.contains(user))
      br.rejectValue("username", "error.user", "There are a user with this username.");
    if(br.hasErrors())
      return "users/form";
    Role role = new Role();
    
    role.setRole("ROLE_NORMAL");
    
    if(!roleService.hasRole(role.getAuthority())){
      roleService.save(role);
    }
    
    user.addRole(role);
    
    userService.saveUser(user);
    
    
    return "redirect:/";
  }
  
  @GetMapping("/user")
  public String userForm(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    
    return "users/form";
    
  }
  
  @GetMapping("/login")
  public String loginForm(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    
    
    return "users/login";
  }

}
