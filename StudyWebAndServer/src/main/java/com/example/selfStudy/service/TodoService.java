package com.example.selfStudy.service;

import com.example.selfStudy.model.TodoEntity;
import com.example.selfStudy.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public List<TodoEntity> create(final TodoEntity entity){
        //Validations
        validate(entity);

        repository.save(entity);
        log.info("Entity Id {} is saved.",entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public List<TodoEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);

        final Optional<TodoEntity> optional = repository.findById(entity.getId());

        if (optional.isPresent()) {
            final TodoEntity todo = optional.get();
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);
        }

        /* 위 if 문과 동일
        optional.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);
        });
        */

        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);

        try {
            repository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity ",entity.getId(),e);

            throw new RuntimeException("error deleting entity " + entity.getId());
        }

        /*
        이건 내가 작성한 코드 Optional을 써본김에 있는지 확인하는 방식으로 만들어 봄
        내용상 위와 동일
        final Optional<TodoEntity> optional = repository.findById(entity.getId());

        if (optional.isPresent()) {
            repository.delete(entity);
        }
        */

        return retrieve(entity.getUserId());
    }
}
