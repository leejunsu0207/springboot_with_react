package com.team.swr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor  // 매개변수가 없는 생성자 구현
@AllArgsConstructor // 클래스의 모든 매개변수로 받는 생성자 구현
@Data               // 클래스의 모든 매개변수의 Getter/Setter 메서드를 구현
public class TodoEntity {

    //  @NoArgsConstructor 어노테이션으로 대체
//    public TodoEntity() {
//    }

    // @AllArgsConstructor 어노테이션으로 대체
//    public TodoEntity(String id, String userId, String title, String done) {
//        this.id = id;
//        this.userId = userId;
//        this.title = title;
//        this.done = done;
//    }


    private String id;  // 이 오브젝트의 아이디
    private String userId;  // 이 오브젝트를 생성한 유저의 아이디
    private String title;   // Todo 타이틀 예) 운동하기
    private String done;    // true - todo를 완료한 경구(checked)

    // @Data 어노테이션으로 대체
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDone() {
//        return done;
//    }
//
//    public void setDone(String done) {
//        this.done = done;
//    }


}
