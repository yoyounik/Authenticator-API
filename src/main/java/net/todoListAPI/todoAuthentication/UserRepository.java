package net.todoListAPI.todoAuthentication;


import net.todoListAPI.todoAuthentication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    void deleteByUsername(Authentication authentication);
}
