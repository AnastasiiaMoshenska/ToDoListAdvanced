package ch.cern.todo.persistence;

import ch.cern.todo.persistence.model.Category;
import ch.cern.todo.persistence.model.CurrentUser;
import ch.cern.todo.persistence.repository.CategoryRepository;
import ch.cern.todo.persistence.repository.CurrentUserRepository;
import ch.cern.todo.persistence.repository.TaskRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SeedDB {
    public TaskRepository taskRepository;
    public CategoryRepository categoryRepository;
    public CurrentUserRepository currentUserRepository;

    public SeedDB(TaskRepository taskRepository, CategoryRepository categoryRepository, CurrentUserRepository currentUserRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.currentUserRepository = currentUserRepository;
    }

    @PostConstruct
    public void categoryInit(){
        categoryRepository.save(new Category(1, "Home", "Something for home"));
        categoryRepository.save(new Category(2, "Animals", "Everything related to animals"));
        categoryRepository.save(new Category(3, "Shopping", "List of items to buy"));
        categoryRepository.save(new Category(4, "Work", "Work tasks"));
        categoryRepository.save(new Category(5, "Sport", "Sport classes"));
    }

    @PostConstruct
    public void currentUserInit(){
        currentUserRepository.save(new CurrentUser(1, "user1", "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG"));
        currentUserRepository.save(new CurrentUser(2, "user2", "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG"));
        currentUserRepository.save(new CurrentUser(3, "user3", "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG"));
    }
}
