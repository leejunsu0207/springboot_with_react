package com.team.swr.persistence;

import com.team.swr.model.Test0Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Test0Repository extends JpaRepository<Test0Entity, String> {


}
