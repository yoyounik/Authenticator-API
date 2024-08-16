package net.todoListAPI.todoAuthentication.controller;

import net.todoListAPI.todoAuthentication.entity.Todo;
import net.todoListAPI.todoAuthentication.entity.User;
import net.todoListAPI.todoAuthentication.service.TodoService;
import net.todoListAPI.todoAuthentication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todovale")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllTodoEntriesOfUser(@PathVariable String username){
        User user = userService.findByUserName(username);
        List<Todo> all = user.getTodoEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<Todo> createEntry(@RequestBody Todo todo, @PathVariable String username){
        try{
            todoService.saveEntry(todo, username);
            return new ResponseEntity<>(todo, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(todo, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<Todo> getTodoEntriesById(@PathVariable ObjectId myId){
        Optional<Todo> todoEntry = todoService.findById(myId);
        if(todoEntry.isPresent()){
            return new ResponseEntity<>(todoEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteTodoEntriesById(@PathVariable ObjectId myId){
        todoService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{myId}")
    public ResponseEntity<?> updateEntriesById(@PathVariable ObjectId myId, @RequestBody Todo todo, @PathVariable String username) {
        Todo old = todoService.findById(myId).orElse(null);
        if (old != null) {
            old.setTitle(todo.getTitle() != null && !todo.getTitle().equals("") ? todo.getTitle() : old.getTitle());
            old.setDescription(todo.getDescription() != null && !todo.getDescription().equals("") ? todo.getDescription() : old.getDescription());
            todoService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
