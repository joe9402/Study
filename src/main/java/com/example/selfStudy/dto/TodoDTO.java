package com.example.selfStudy.dto;

import com.example.selfStudy.model.TodoEntity;
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
    private boolean done;

    public TodoDTO(final TodoEntity entitiy){
        this.id = entitiy.getId();
        this.title = entitiy.getTitle();
        this.done = entitiy.isDone();
    }
}
