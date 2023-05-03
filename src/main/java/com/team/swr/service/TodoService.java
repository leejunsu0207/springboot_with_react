package com.team.swr.service;

import com.team.swr.model.TodoEntity;
import com.team.swr.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {

    TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public String testService(){
        // TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        // TodoEntity 저장
        todoRepository.save(entity);
        // TodoEntity 검색
        TodoEntity savedEntity = todoRepository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity){

        validate(entity);

        todoRepository.save(entity);

        log.info("Entity Id : {} is saved", entity.getId());

        return todoRepository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId){

        log.info("Entity userId : {}", userId);

        return todoRepository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity){
        // 1. 저장할 entity가 유효 한지 확인
        validate(entity);

        // 2. 넘겨 받은 entity id를 이용해 TodoEntity를 가져온다. 존재하지 않는 entity는 업데이트 할수 없기 때문
        final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
        original.ifPresent(todo ->{
           // 3. 변환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어 씌운다.
           todo.setTitle(entity.getTitle());
           todo.setDone(entity.isDone());

           // 4. 데이터베이스 값에 새값을 저장
            todoRepository.save(todo);
        });

        // 2.3.2 Retireve Todo에서 만든 method를 이용해 유저의 모든 Todo리스트를 return
        return retrieve(entity.getUserId());

    }

    public List<TodoEntity> delete(final TodoEntity entity){
        // 1. 저장할 entity가 유효 한지 확인
        validate(entity);

        try {
            // 2. entity delete
            todoRepository.delete(entity);
        } catch(Exception e){
            // 3. exception 발생 시 id와  exception loggin 한다.
            log.error("error deleting entity ", entity.getId(), e);

            // 4. controller 에 exception 처리 database 내부 로직 캡슐화라기 위해 e를 return하지 않고 new exception 보르젝트를 return한다.
            throw new RuntimeException("error deleting entity " + entity.getId());
        }

        // 5. new TodoList return 한다.
        return retrieve(entity.getUserId());

    }

    private void validate(final TodoEntity entity){
        // validations
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

}
