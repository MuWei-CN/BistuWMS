package com.example.spring.service.UserService;

import com.example.spring.bean.UserBean;
import com.example.spring.mapper.UserMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class LogRegService {
    private UserMapper userMapper;

    @Autowired
    public void setMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public boolean LoginService(String name,String password) throws Exception{
        UserBean tempuser = new UserBean();
        tempuser.setName(name);
        tempuser.setPassword(password);
        return userMapper.findUserBy(tempuser);
    }

    public boolean RegisterService(String name,String password) throws Exception{
        UserBean tempuser = new UserBean();
        tempuser.setName(name);
        tempuser.setPassword(password);
        return userMapper.addUser(tempuser);
    }

    public boolean ChangePasswordService(String name,String password,String newpassword) throws Exception{
        UserBean tempuser = new UserBean();
        tempuser.setName(name);
        tempuser.setPassword(password);
        return userMapper.updatePasswordBy(tempuser,newpassword) > 0;
    }
}
