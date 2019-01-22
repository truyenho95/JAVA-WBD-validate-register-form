package com.truyenho.validateform.controller;

import com.truyenho.validateform.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {
  @RequestMapping("/")
  public ModelAndView showRegisterForm() {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("user", new User());
    return modelAndView;
  }

  @PostMapping("/")
  public ModelAndView checkValidation(@Valid @ModelAttribute User user, BindingResult bindingResult) {
    ModelAndView modelAndView;
    user.validate(user, bindingResult);
    if (bindingResult.hasFieldErrors()) {
      modelAndView = new ModelAndView("index");
      return modelAndView;
    } else {
      modelAndView = new ModelAndView("result");
      modelAndView.addObject("user", user);
      return modelAndView;
    }
  }
}
