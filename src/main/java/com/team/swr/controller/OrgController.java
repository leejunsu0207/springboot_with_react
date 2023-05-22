package com.team.swr.controller;

import com.team.swr.dto.OrgDTO;
import com.team.swr.dto.ResponseDTO;
import com.team.swr.model.OrgEntity;
import com.team.swr.service.OrgService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("org")
public class OrgController {

    OrgService orgService;

    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }

    @GetMapping
    public ResponseEntity<?> retrieveAll(){

        List<OrgEntity> entities = orgService.retrieveAll();

        List<OrgDTO> dtos = entities.stream().map(OrgDTO::new).collect(Collectors.toList());

        ResponseDTO<OrgDTO> response = ResponseDTO.<OrgDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);

    }

}
