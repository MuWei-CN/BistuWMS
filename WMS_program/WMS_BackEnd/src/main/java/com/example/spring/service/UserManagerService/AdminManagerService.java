package com.example.spring.service.UserManagerService;

import com.example.spring.bean.UserBean;
import com.example.spring.mapper.UserManagerMapper.AdminManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminManagerService implements UserManagerService{
    @Autowired
    private AdminManagerMapper adminManagerMapper;

    @Override
    public boolean addUser(String name, String password) throws Exception {
        UserBean tmpUser = new UserBean();
        tmpUser.setName(name);
        tmpUser.setPassword(password);
        return adminManagerMapper.addCommon(tmpUser);
    }

    @Override
    public boolean deleteUser(String name) throws Exception {
        return adminManagerMapper.delCommon(name);
    }

    @Override
    public int updateUser(String name, String newname, String newpassword) throws Exception {
        UserBean tmpUser = new UserBean();
        tmpUser.setName(newname);
        tmpUser.setPassword(newpassword);
        return adminManagerMapper.updCommon(name,tmpUser);
    }

    @Override
    public Map<String, UserBean> selectAllUser() throws Exception {
        return adminManagerMapper.selAllCommon();
    }

    @Override
    public UserBean selectUserBy(String name) throws Exception {
        return adminManagerMapper.selAllCommon().get(name);
    }
}
