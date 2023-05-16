package com.team.swr.dto;

import com.team.swr.model.SrEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SrDTO {

    private String srId;    // srId
    private String orgCd;   // 기관 코드
    private String orgNm;   // 기관 명
    private String proc;    // 진행상태
    private String receiver;    // 수신자
    private String caller;  // 발신자
    private String mgr; // 처리자
    private String consDay; // 상담일
    private String procDay; // 처리일
    private String consDetail;  // 상담내역
    private String procDetail;  // 처리내역

    public SrDTO(final SrEntity entity) {
        this.srId = entity.getSrId();
        this.orgCd = entity.getOrgCd();
        this.orgNm = entity.getOrgNm();
        this.proc = entity.getProc();
        this.receiver = entity.getReceiver();
        this.caller = entity.getCaller();
        this.mgr = entity.getMgr();
        this.consDay = entity.getConsDay();
        this.procDay = entity.getProcDay();
        this.consDetail = entity.getConsDetail();
        this.procDetail = entity.getProcDetail();
    }

    public static SrEntity toEntity(final SrDTO dto){
        return SrEntity.builder()
                .srId(dto.getSrId())
                .orgCd(dto.getOrgCd())
                .orgNm(dto.getOrgNm())
                .proc(dto.getProc())
                .receiver(dto.getReceiver())
                .caller(dto.getCaller())
                .mgr(dto.getMgr())
                .consDay(dto.consDay)
                .procDay(dto.procDay)
                .consDetail(dto.consDetail)
                .procDetail(dto.procDetail)
                .build();
    }

}
