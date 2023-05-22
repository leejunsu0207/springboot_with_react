package com.team.swr.persistence;

import com.team.swr.model.SrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SrRepository extends JpaRepository<SrEntity, String> {

    @Query(value = "SELECT * FROM SRSVC WHERE SRSVC.ORGCD = ?1", nativeQuery = true)
    List<SrEntity> findAllByOrgCd(String orgCd);

}
