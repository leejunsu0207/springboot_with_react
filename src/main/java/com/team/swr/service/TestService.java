package com.team.swr.service;

import com.team.swr.model.SrEntity;
import com.team.swr.model.Test0Entity;
import com.team.swr.model.Test1Entity;
import com.team.swr.persistence.Test0Repository;
import com.team.swr.persistence.Test1Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TestService {

    Test0Repository test0Repository;
    Test1Repository test1Repository;

    public TestService(Test0Repository test0Repository, Test1Repository test1Repository) {
        this.test0Repository = test0Repository;
        this.test1Repository = test1Repository;
    }

    public List<Test0Entity> retrieveTest0(){
        log.info("retrieveTest0");
        return test0Repository.findAll();
    }

    public List<Test1Entity> retrieveTest1(){
        log.info("retrieveTest0");
        return test1Repository.findAll();
    }

    





}
