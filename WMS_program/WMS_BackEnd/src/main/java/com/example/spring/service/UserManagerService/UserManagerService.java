package com.example.spring.service.UserManagerService;

import com.example.spring.bean.UserBean;

import java.util.Map;

public interface UserManagerService {
    public boolean addUser(String name,String password) throws Exception;
    public boolean deleteUser(String name) throws Exception;
    public int updateUser(String name,String newname,String newpassword) throws Exception;
    public UserBean selectUserBy(String name) throws Exception;
    public Map<String,UserBean> selectAllUser() throws Exception;
}
