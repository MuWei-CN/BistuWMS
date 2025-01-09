package com.example.spring.service.UserManagerService;

import com.example.spring.bean.UserBean;
import com.example.spring.mapper.UserManagerMapper.RootManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RootManagerService implements UserManagerService{
    @Autowired
    private RootManagerMapper rootManagerMapper;

    @Override
    public boolean addUser(String name, String password) throws Exception{
        UserBean tmpUser = new UserBean();
        tmpUser.setName(name);
        tmpUser.setPassword(password);
        return rootManagerMapper.addAdmin(tmpUser);
    }

    @Override
    public boolean deleteUser(String name) throws Exception{
        return rootManagerMapper.delAdmin(name);
    }

    @Override
    public int updateUser(String name, String newname, String newpassword) throws Exception{
        UserBean tmpUser = new UserBean();
        tmpUser.setName(newname);
        tmpUser.setPassword(newpassword);
        return rootManagerMapper.updAdmin(name,tmpUser);
    }

    @Override
    public UserBean selectUserBy(String name) throws Exception{
        return rootManagerMapper.selAllAdmin().get(name);
    }

    @Override
    public Map<String, UserBean> selectAllUser() throws Exception{
        return rootManagerMapper.selAllAdmin();
    }
}
