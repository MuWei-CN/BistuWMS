package com.example.spring.mapper.UserMapper;

import com.example.spring.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    boolean findUserBy(UserBean userBean);
    boolean addUser(UserBean userBean);
    int updatePasswordBy(UserBean userBean,String newpassword);
}
