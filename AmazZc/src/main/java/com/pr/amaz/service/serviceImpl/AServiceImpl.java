package com.pr.amaz.service.serviceImpl;

import com.pr.amaz.mapper.AMapper;
import com.pr.amaz.model.A;
import com.pr.amaz.service.AService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("aService")
public class AServiceImpl implements AService {

    private Logger logger = LoggerFactory.getLogger(AServiceImpl.class);

    @Autowired
    private AMapper aMapper;

    @Override
    public void insertA() {
        logger.info("插入A表数据");
        A a = new A();
        a.setPortCode("111");
        a.setPortValue("111");
        aMapper.insert(a);
    }
}
