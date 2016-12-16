package br.com.minitwitter.controller;

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
    User user = new User();
    model.addAttribute("user", user);
    return "index";
  }

}
