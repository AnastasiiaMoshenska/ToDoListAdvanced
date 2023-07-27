package ch.cern.todo.controller;

import ch.cern.todo.persistence.model.Category;
import ch.cern.todo.persistence.model.Task;
import ch.cern.todo.persistence.repository.TaskRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TaskControllerTest {

    @Autowired
    public TaskRepository repository;

    @BeforeAll
    public void setUp() {
        repository.save(new Task(0, "Test task 0", "Test description", new Timestamp(2000), new Category(1, "Home", "Something for home")));
    }

    @Test
    @Order(1)
    void getTasks(){
        List<Task> tasks = (List<Task>)repository.findAll();
        assertEquals(1, tasks.size());
    }

    @Test
    @Order(2)
    void addTasks(){
        repository.save(new Task(1, "Test task 1", "Test description", new Timestamp(2000), new Category(1, "Home", "Something for home")));
        repository.save(new Task(2, "Test task 2", "Test description", new Timestamp(2000), new Category(1, "Home", "Something for home")));
        List<Task> tasks = (List<Task>)repository.findAll();
        assertEquals(3, tasks.size());
    }

    @Test
    @Order(3)
        void deleteTaskById(){
        repository.deleteById(1);
        List<Task> tasks = (List<Task>)repository.findAll();
        assertEquals(2, tasks.size());
    }

    @Test
    @Order(4)
    void updateTask(){
        Task task = new Task(2, "Updated Test task 2", "Test description", new Timestamp(2000), new Category(1, "Home", "Something for home"));
        Task currentTask = repository.findById(2).orElseThrow(RuntimeException::new);
        currentTask.setCategory(task.getCategory());
        currentTask.setDeadline(task.getDeadline());
        currentTask.setName(task.getName());
        currentTask.setDescription(task.getDescription());
        repository.save(currentTask);
        Task updatedTask = repository.findById(2).orElseThrow(RuntimeException::new);
        assertEquals(updatedTask.getName(), "Updated Test task 2");
    }
}