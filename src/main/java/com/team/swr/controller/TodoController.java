package com.team.swr.controller;

import com.team.swr.dto.ResponseDTO;
import com.team.swr.dto.TodoDTO;
import com.team.swr.model.TodoEntity;
import com.team.swr.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

//    @GetMapping
//    public ResponseEntity<?> testTodo0(){
//        List list = new ArrayList();
//        list.add("test todo response!");
//
//        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//        return ResponseEntity.ok(response);
//
//    }

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = todoService.testService();

        List list = new ArrayList();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);

    }

    @PostMapping
    public ResponseEntity<?> createTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto){
        try{

            // 1. TodoEntity로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            // 2. id를 null 초기화 한다. 생성당시에는 id 가 없어야 한다.
            entity.setId(null);

            // 3. 임시 유저 아이디를 생성해준다. 이 부분은 4장 인증과 인가에서 수정할 예정, 지금은 인증과 인가 기능이 없으므로 한 유저(temporary-user)만 로그인 없이 사용 가능하다
            entity.setUserId(userId);

            // 4. 서비스를 이용해 Todo entity를 생성한다.
            List<TodoEntity> entityes = todoService.create(entity);

            // 5. java stream을 이용해 리턴된 리스트를 TodoDTO리스트로 반환
            List<TodoDTO> dtos = entityes.stream().map(TodoDTO::new).collect(Collectors.toList());

            // 6. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            // 7. ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {

            // 8. 혹시 예외가 나는 경우 dto 대신 error레 메시지 넣어 리턴
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);

        }


    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList(@AuthenticationPrincipal String userId){


        // 1. service method 의 retrieve method를 이용해 Todolist를 가져온다
        List<TodoEntity> entityes = todoService.retrieve(userId);

        // 2. java stream을 이용해 return 된 entity list를 TodoDTO list 로 변환
        List<TodoDTO> dtos = entityes.stream().map(TodoDTO::new).collect(Collectors.toList());

        // 3. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        // 4. ResponseDTO를 리턴한다.
        return ResponseEntity.ok().body(response);


    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto){


        // 1. dto를 Entity로 변환
        TodoEntity entity = TodoDTO.toEntity(dto);

        // 2. id를 temporaryUserId로 초기화 인증, 인가 수정예정
        entity.setUserId(userId);

        // 3. 서비스를 이용해 entity를 update
        List<TodoEntity> entities = todoService.update(entity);

        // 4. java stream을 이용해 return 된 entity list를 TodoDTO list로 변환
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        // 5. 변환된 TodoDTO list를 이용해 ResponseDTO를 초기화 한다.
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        // 6. ResponseDTO return
        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto){

        try {


            // 1. TodoEntity 로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            // 2. 임시 아이디 설정
            entity.setUserId(userId);

            // 3. service를 이용해 entity delete
            List<TodoEntity> entities = todoService.delete(entity);

            // 4. java stream 을 이용해 return list TodoDTO list로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            // 5. 변환된 TodoDTO list를 이용해 ResponseDTO를 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            // 6. ResponseDTO를 return
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            // 7. 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 return
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }

    }





}
