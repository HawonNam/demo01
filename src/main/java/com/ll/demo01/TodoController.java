package com.ll.demo01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private long todosLastId;
    private List<Todo> todos;

    public TodoController(){
        todos = new ArrayList<>();

    }

    @GetMapping("")
    public List<Todo> getTodos() {
        return todos;
    }


    @GetMapping("/add")
    public Todo add(
            String body
    ){
        Todo todo = Todo
                .builder()
                .id(++todosLastId)
                .body(body)
                .build();

        todos.add(todo);

        return todo;
    }
}
