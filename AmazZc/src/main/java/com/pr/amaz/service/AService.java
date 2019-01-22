package com.pr.amaz.service;


import com.pr.amaz.aop.DataSource;


public interface AService {

    @DataSource(name = "idrep")
    void insertA();

}
