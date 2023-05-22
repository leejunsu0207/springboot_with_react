package com.team.swr.dto;

import com.team.swr.model.OrgEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrgDTO {

    private String orgCd;   // 기관 코드
    private String orgNm;   // 기관 명

    public OrgDTO(final OrgEntity entity) {
        this.orgCd = entity.getOrgCd();
        this.orgNm = entity.getOrgNm();
    }



}
