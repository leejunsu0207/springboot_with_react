package com.team.swr.testController;

import com.team.swr.dto.ResponseDTO;
import com.team.swr.testDto.TestDTO0;
import com.team.swr.testDto.TestDTO1;
import com.team.swr.testDto.TestRequestBodyDTO;
import com.team.swr.model.testModel.Test0Entity;
import com.team.swr.model.testModel.Test1Entity;
import com.team.swr.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("test") // 리소스
public class TestController {

    TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/testGetMapping")
    public String testController(){
        return "Hello World";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
        return "Hello World! ID " + id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false) int id){
        return "Hello World! ID " + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! i'm ResponseDTO");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {

        List<String> list = new ArrayList<>();
        list.add("Hello world! I'm ResponseEntity. And you got 400");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        // http status 400 return
        return ResponseEntity.badRequest().body(response);

    }


    @GetMapping("/test0")
    public ResponseEntity<?> retrieveAll0() {

        List<Test0Entity> entities = testService.retrieveTest0();

        List<TestDTO0> dtos = entities.stream().map(TestDTO0::new).collect(Collectors.toList());

        ResponseDTO<TestDTO0> response = ResponseDTO.<TestDTO0>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/test1")
    public ResponseEntity<?> retrieveAll1() {

        List<Test1Entity> entities = testService.retrieveTest1();

        List<TestDTO1> dtos = entities.stream().map(TestDTO1::new).collect(Collectors.toList());

        ResponseDTO<TestDTO1> response = ResponseDTO.<TestDTO1>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);

    }


}
