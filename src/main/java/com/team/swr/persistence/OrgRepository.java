package com.team.swr.persistence;

import com.team.swr.model.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<OrgEntity, String> {



}
