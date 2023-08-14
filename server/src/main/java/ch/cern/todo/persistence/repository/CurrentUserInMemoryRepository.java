package ch.cern.todo.persistence.repository;

import ch.cern.todo.persistence.model.CurrentUser;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class CurrentUserInMemoryRepository {
    private final HashMap<String, CurrentUser> REGISTERED_USERS = new HashMap<String, CurrentUser>(2);

    @PostConstruct
    public void setUpUsers(){
        REGISTERED_USERS.put("user1", buildCurrentUser("user1", "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG"));
    }

    public CurrentUser findUserByUsername(final String username){
        return REGISTERED_USERS.get(username);
    }

    private static CurrentUser buildCurrentUser(final String username, final String password){
        final CurrentUser currentUser = new CurrentUser();
        currentUser.setUserName(username);
        currentUser.setPassword(password);

        return currentUser;
    }
}
