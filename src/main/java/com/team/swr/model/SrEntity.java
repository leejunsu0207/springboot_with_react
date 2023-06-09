package com.team.swr.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Data
@Table(name="SRSVC")
public class SrEntity {

    public SrEntity() {
    }

    public SrEntity(String srId, String userId, String orgCd, String orgNm, String proc, String receiver, String caller, String mgr, String consDay, String procDay, String consDetail, String procDetail) {
        this.srId = srId;
        this.userId = userId;
        this.orgCd = orgCd;
        this.orgNm = orgNm;
        this.proc = proc;
        this.receiver = receiver;
        this.caller = caller;
        this.mgr = mgr;
        this.consDay = consDay;
        this.procDay = procDay;
        this.consDetail = consDetail;
        this.procDetail = procDetail;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "SRID")
    private String srId;    // srId
    @Column(name = "USERID", nullable = true)
    private String userId; // object create user id
    @Column(name = "ORGCD", nullable = true)
    private String orgCd;   // 기관 코드
    @Column(name = "ORGNM", nullable = true)
    private String orgNm;   // 기관 명
    @Column(name = "PROC", nullable = true)
    private String proc;   // 진행상태
    @Column(name = "RECEIVER", nullable = true)
    private String receiver;    // 수신자
    @Column(name = "CALLER", nullable = true)
    private String caller;  // 발신자
    @Column(name = "MGR", nullable = true)
    private String mgr; // 처리자
    @Column(name = "CONSDAY", nullable = true)
    private String consDay; // 상담일
    @Column(name = "PROCDAY", nullable = true)
    private String procDay; // 처리일
    @Lob
    @Column(name = "CONSDETAIL", nullable = true)
    private String consDetail;  // 상담내역
    @Lob
    @Column(name = "PROCDETAIL")
    private String procDetail;  // 처리내역

}
