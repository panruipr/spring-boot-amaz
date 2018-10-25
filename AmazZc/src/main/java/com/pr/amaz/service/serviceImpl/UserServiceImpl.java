package com.pr.amaz.service.serviceImpl;

import com.pr.amaz.mapper.UserInfoMapper;
import com.pr.amaz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userdao;

    @Override
    public List findAllUser() {
        return null;
    }
}
