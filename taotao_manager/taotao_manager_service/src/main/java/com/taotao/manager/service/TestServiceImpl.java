package com.taotao.manager.service;

import com.taotao.manager.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public String queryDate() {
        return testMapper.queryDate();
    }
}
