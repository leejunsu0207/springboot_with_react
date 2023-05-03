package com.team.swr.persistence;

import com.team.swr.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                         // 매핑할 테이블, 엔티티 pk 타입
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    List<TodoEntity> findByUserId(String userId);

    // @Query를 이요한 쿼리 메서드 작성
//    @Query("select * from TodoEntity t where t.userId = ?1")
//    List<TodoEntity> findByUserQuery(String userId);

}
