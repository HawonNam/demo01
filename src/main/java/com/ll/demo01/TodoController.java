package com.ll.demo01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todos") // 이 컨트롤러에서 처리할 모든 요청은 /todos로 시작된다.
public class TodoController {

    private long todosLastId;
    private List<Todo> todos;

    public TodoController(){ // 컨트롤러가 생성될 때 todos 리스트를 초기화한다. 즉 웹 서버가 켜질 때 빈 리스트가 준비된다.
        todos = new ArrayList<>();

    }

    @GetMapping("")
    public List<Todo> getTodos() { //모든 todo 목록을 반환
        return todos;
    }

    @GetMapping("detail")
    public Todo getTodo(
            long id
    ) {
        return todos
                .stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("{id}")
    public Todo getTodo2(
            @PathVariable long id
    ) {
        return todos
                .stream()
                .filter(
                        todo -> todo.getId() == id
                )
                .findFirst()
                .orElse(null);
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

    @GetMapping("/remove/{id}")
    public boolean remove(@PathVariable long id){
        boolean removed = todos.removeIf(todo -> todo.getId() == id);

        return removed;
    }

    @GetMapping("/modify{id}")
    public boolean modify(
            @PathVariable long id,
            String body
    ){
        Todo todo = todos
                .stream()
                .filter(_todo -> _todo.getId() == id)
                .findFirst()
                .orElse(null);

        if(todo == null) return false;

        todo.setBody(body);

        return true;
    }
}

