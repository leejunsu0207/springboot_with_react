package com.team.swr.persistence;

import com.team.swr.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUsername(String username);

    boolean existsByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);


}
