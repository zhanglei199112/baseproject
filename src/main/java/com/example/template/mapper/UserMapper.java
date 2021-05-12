package com.example.template.mapper;

import com.example.template.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

  User findById(@Param("id") int id);
}
