package com.apress.todo;

import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends
        CrudRepository<ToDo, String> {
}
