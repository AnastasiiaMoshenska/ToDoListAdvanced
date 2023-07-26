package ch.cern.todo.controller;

import ch.cern.todo.persistence.model.Category;
import ch.cern.todo.persistence.model.Task;
import ch.cern.todo.persistence.repository.TaskRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TaskControllerTest {

    @Autowired
    public TaskRepository repository;

    @Test
    @Order(1)
    void getTasks(){
        repository.save(new Task(1, "Test task 1", "Test description", new Timestamp(2000), new Category(1, "Home", "Something for home")));
        repository.save(new Task(2, "Test task 2", "Test description", new Timestamp(2000), new Category(1, "Home", "Something for home")));
        List<Task> tasks = (List<Task>)repository.findAll();
        assertEquals(2, tasks.size());
    }

    @Test
    @Order(2)
        void deleteTaskById(){
        repository.deleteById(1);
        List<Task> tasks = (List<Task>)repository.findAll();
        assertEquals(1, tasks.size());
    }
}