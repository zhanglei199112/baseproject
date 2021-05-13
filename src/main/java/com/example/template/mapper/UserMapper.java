package com.example.template.mapper;

import com.example.template.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

  User findById(@Param("id") int id);
}
