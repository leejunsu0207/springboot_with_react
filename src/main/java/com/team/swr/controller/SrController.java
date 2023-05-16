package com.team.swr.controller;

import com.team.swr.dto.ResponseDTO;
import com.team.swr.dto.SrDTO;
import com.team.swr.dto.TodoDTO;
import com.team.swr.model.SrEntity;
import com.team.swr.service.SrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sr")
public class SrController {

    SrService srService;

    public SrController(SrService srService) {
        this.srService = srService;
    }


    @GetMapping
    public ResponseEntity<?> retrieveAll(@RequestParam(required = false, value = "orgCd") String orgCd){

        List<SrEntity> entities;

        if(!"".equals(orgCd) || orgCd != null){
            entities = srService.retrieveOrgCd();
        }else {
            entities = srService.retrieveAll();
        }

        List<SrDTO> dtos = entities.stream().map(SrDTO::new).collect(Collectors.toList());

        ResponseDTO<SrDTO> response = ResponseDTO.<SrDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);

    }


    @PostMapping
    public ResponseEntity<?> crate(@RequestBody SrDTO dto){

        try {

            String temporaryUserId = "temporary-user";

            SrEntity entity = SrDTO.toEntity(dto);

            entity.setSrId(null);

            entity.setUserId(temporaryUserId);

            List<SrEntity> entities = srService.create(entity);

            List<SrDTO> dtos = entities.stream().map(SrDTO::new).collect(Collectors.toList());

            ResponseDTO<SrDTO> response = ResponseDTO.<SrDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);

        }catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<SrDTO> response = ResponseDTO.<SrDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody SrDTO dto){

        String temporaryUserId = "temporary-user";

        SrEntity entity = SrDTO.toEntity(dto);

        entity.setUserId(temporaryUserId);

        List<SrEntity> entities = srService.update(entity);

        List<SrDTO> dtos = entities.stream().map(SrDTO::new).collect(Collectors.toList());

        ResponseDTO<SrDTO> response = ResponseDTO.<SrDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody SrDTO dto){

        try {
            String temporaryUserId = "temporary-user";

            SrEntity entity = SrDTO.toEntity(dto);

            entity.setUserId(temporaryUserId);

            List<SrEntity> entities = srService.delete(entity);

            List<SrDTO> dtos = entities.stream().map(SrDTO::new).collect(Collectors.toList());

            ResponseDTO<SrDTO> response = ResponseDTO.<SrDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);


        }catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<SrDTO> response = ResponseDTO.<SrDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);

        }

    }






}
