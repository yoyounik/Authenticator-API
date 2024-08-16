package net.todoListAPI.todoAuthentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todo_app")
@Data
@NoArgsConstructor
public class Todo {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String description;


}
