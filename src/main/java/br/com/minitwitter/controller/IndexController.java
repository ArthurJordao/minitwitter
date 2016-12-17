package br.com.minitwitter.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.minitwitter.model.User;

@Controller
@RequestMapping("/")
public class IndexController {
  
  @GetMapping
  public String indexPage(Model model) {
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();

    if (!(auth instanceof AnonymousAuthenticationToken))
      return "forward:/feed";
    
    User user = new User();
    model.addAttribute("user", user);
    return "index";
  }

}
