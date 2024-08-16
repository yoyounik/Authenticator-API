package net.todoListAPI.todoAuthentication;

import net.todoListAPI.todoAuthentication.entity.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, ObjectId> {
}
