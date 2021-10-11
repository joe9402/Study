package com.example.selfStudy.service;

import com.example.selfStudy.model.TodoEntity;
import com.example.selfStudy.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService(){
        TodoEntity entity = TodoEntity.builder().title("My firest todo item").build();
        repository.save(entity);
        TodoEntity saveEntity = repository.findById(entity.getId()).get();
        return saveEntity.getTitle();//"Test Service";
    }
}
