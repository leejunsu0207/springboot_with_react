package com.team.swr.service;

import com.team.swr.model.SrEntity;
import com.team.swr.model.TodoEntity;
import com.team.swr.persistence.SrRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SrService {

    SrRepository srRepository;

    public SrService(SrRepository srRepository) {
        this.srRepository = srRepository;
    }


    public List<SrEntity> retrieveAll(){
        log.info("retrieveAll");
        return srRepository.findAll();
    }

    public List<SrEntity> retrieveOrgCd(){
        log.info("retrieveOrgCd");
        return srRepository.findAll();
    }

    public List<SrEntity> create(final SrEntity entity){

        validate(entity);

        srRepository.save(entity);

        log.info("Entity id : {}", entity.getSrId());

        return srRepository.findAll();

    }

    public List<SrEntity> update(final SrEntity entity){

        validate(entity);

        log.info("update id : {}", entity.getSrId());

        final Optional<SrEntity> original = srRepository.findById(entity.getSrId());
        original.ifPresent(sr -> {
            sr.setProc(entity.getProc());
            sr.setMgr(entity.getMgr());
            sr.setProcDay(entity.getProcDay());
            sr.setConsDetail(entity.getConsDetail());
            sr.setProcDetail(entity.getProcDetail());

            srRepository.save(sr);
        });

        return retrieveAll();
    }

    public List<SrEntity> delete(final SrEntity entity){

        validate(entity);

        try {

            srRepository.delete(entity);

        }catch (Exception e) {
            log.error("error deleting entity", entity.getSrId(), e);
            throw new RuntimeException("error deleting entity " + entity.getSrId());
        }

        return retrieveAll();
    }


    private void validate(final SrEntity entity){
        // validations
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }

    }


}
