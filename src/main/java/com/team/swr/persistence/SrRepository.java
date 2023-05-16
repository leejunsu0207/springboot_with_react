package com.team.swr.persistence;

import com.team.swr.model.SrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SrRepository extends JpaRepository<SrEntity, String> {


}
