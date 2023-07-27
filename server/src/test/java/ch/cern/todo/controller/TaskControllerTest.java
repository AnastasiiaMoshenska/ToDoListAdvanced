package ch.cern.todo.controller;

import ch.cern.todo.persistence.model.Category;
import ch.cern.todo.persistence.model.Task;
import ch.cern.todo.persistence.repository.TaskRepository;
import ch.cern.todo.service.TaskService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    public TaskRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService;


    @BeforeAll
    public void setUp() {
        repository.save(new Task(1, "Test task 1", "Test description", new Timestamp(2000), new Category(1, "Home", "Something for home")));
        repository.save(new Task(2, "Test task 2", "Test description", new Timestamp(2000), new Category(1, "Home", "Something for home")));
    }

    @Test
    @Order(1)
    public void testGetTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test task 1"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].name").value("Test task 2"))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    @Order(2)
    void testAddTask() throws Exception {
        String requestBody = "{ \"name\": \"New Task\", " +
                "\"description\": \"New Description\", " +
                "\"deadline\": \"2023-07-27T12:34:56Z\", " +
                "\"category\": { " +
                "    \"id\": 3, " +
                "    \"name\": \"Work\", " +
                "    \"description\": \"Something for work\" " +
                "} " +
                "}";

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        List<Task> tasks = (List<Task>) taskService.getTasks();
        assertEquals(3, tasks.size());
    }

    @Test
    @Order(3)
    void testDeleteTaskById() throws Exception{
        int id = 1;
        mockMvc.perform(delete("/tasks/" + id))
                .andExpect(status().isOk());

        List<Task> tasks = (List<Task>) taskService.getTasks();
        assertEquals(2, tasks.size());
    }

    @Test
    @Order(4)
    void testUpdateTask() throws Exception{
        int id = 0;

        String requestBody = "{ \"name\": \"Updated Task\", " +
                "\"description\": \"Updated Description\", " +
                "\"deadline\": \"2023-07-27T12:34:56Z\", " +
                "\"category\": { " +
                "    \"id\": 3, " +
                "    \"name\": \"Work\", " +
                "    \"description\": \"Something for work\" " +
                "} " +
                "}";
        mockMvc.perform(put("/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());

        Task updatedTask = taskService.getTaskById(id);
        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getName());

    }
}