package com.team.swr.dto;

import com.team.swr.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {

    private String id;
    private String title;
    private String done;

    public TodoDTO(final TodoEntity entity) {
        this.id = id;
        this.title = title;
        this.done = done;
    }

}
