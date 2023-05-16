package com.team.swr.persistence;

import com.team.swr.model.Test1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Test1Repository extends JpaRepository<Test1Entity, String> {

}
