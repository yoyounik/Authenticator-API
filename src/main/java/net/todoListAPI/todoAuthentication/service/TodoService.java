package net.todoListAPI.todoAuthentication.service;

import net.todoListAPI.todoAuthentication.TodoRepository;
import net.todoListAPI.todoAuthentication.entity.Todo;
import net.todoListAPI.todoAuthentication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(Todo todo, String userName){
        User user = userService.findByUserName(userName);
        Todo saved = todoRepository.save(todo);
        user.getTodoEntries().add(saved);

        userService.saveUser(user);
    }

    public void saveEntry(Todo todo){
        todoRepository.save(todo);
    }


    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(ObjectId id){
        return todoRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        todoRepository.deleteById(id);
    }
}
