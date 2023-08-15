package ch.cern.todo.persistence.repository;

import ch.cern.todo.persistence.model.CurrentUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CurrentUserRepository extends CrudRepository<CurrentUser, Integer> {
    @Query("select e from CurrentUser e where e.userName = ?1")
    CurrentUser findUserByUsername(String username);
}
