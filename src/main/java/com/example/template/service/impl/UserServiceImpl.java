package com.example.template.service.impl;

import com.example.template.entity.User;
import com.example.template.mapper.UserMapper;
import com.example.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User Sel(int id) {
    return userMapper.findById(id);
  }

  @Override
  public void add(User user) {
    userMapper.insert(user);
  }
}
