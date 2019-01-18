package com.pr.amaz.service;

import com.pr.amaz.aop.DataSource;

import java.util.List;

public interface UserService {

    @DataSource
    List findAllUser();

}
