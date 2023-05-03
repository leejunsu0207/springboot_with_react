package com.team.swr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor  // 매개변수가 없는 생성자 구현
@AllArgsConstructor // 클래스의 모든 매개변수로 받는 생성자 구현
@Data               // 클래스의 모든 매개변수의 Getter/Setter 메서드를 구현
@Entity             // entity class는 그 자체가 테이블을 정의해야한다. pk, fk 구분, java class로 entity정의시 주의점 매개변수 없는 생성자 필요, getter/setter 필요, pk지정 필요
@Table(name="Todo") // @Table 어노테이션 추가 하지 않거나 name 명시하지 않으면 @Entity의 이름을 테이블이름 으로 간주 @Entity에 이름을 지정하지 않으면 클래스의 이름을 테이블이름으로 간주
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

    @Id
    @GeneratedValue(generator = "system-uuid")  // id 자동 생성
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // 기본 제공 generator(INCREMENTAL, SEQUENCE, IDENTITY)등 을 사용하지 않고 문자열 UUID 를 사용하기 위해 명시
    private String id;  // 이 오브젝트의 아이디
    private String userId;  // 이 오브젝트를 생성한 유저의 아이디
    private String title;   // Todo 타이틀 예) 운동하기
    private boolean done;    // true - todo를 완료한 경구(checked)

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
