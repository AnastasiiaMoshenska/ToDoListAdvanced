package ch.cern.todo.service;

import ch.cern.todo.persistence.model.CurrentUser;
import ch.cern.todo.persistence.repository.CurrentUserInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements UserDetailsService {

    private CurrentUserInMemoryRepository currentUserRepository;

    @Autowired
    public CurrentUserServiceImpl(CurrentUserInMemoryRepository currentUserRepository) {
        this.currentUserRepository = currentUserRepository;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        CurrentUser currentUser = currentUserRepository.findUserByUsername(username);

        if(currentUser == null){
            throw new UsernameNotFoundException("No username");
        }
        return currentUser;
    }
}
