package com.example.template.controller;

import com.example.template.baseentity.RestResult;
import com.example.template.entity.User;
import com.example.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("getUser/{id}")
  public User GetUser(@PathVariable int id){
    User sel = userService.Sel(id);
    return sel;
  }

  @GetMapping("add")
  public RestResult addUser(){
    User user = new User();
    user.setAge(18);
    user.setName("关雅琪");
    userService.add(user);
    return RestResult.ok();
  }
}
