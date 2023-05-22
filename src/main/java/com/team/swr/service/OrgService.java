package com.team.swr.service;

import com.team.swr.model.OrgEntity;
import com.team.swr.persistence.OrgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrgService {

    OrgRepository orgRepository;

    public OrgService(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }


    public List<OrgEntity> retrieveAll(){
        log.info("org retrieveAll");
        return orgRepository.findAll();
    }

}
