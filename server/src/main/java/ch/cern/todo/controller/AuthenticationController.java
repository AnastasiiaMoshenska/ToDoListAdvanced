package ch.cern.todo.controller;

import ch.cern.todo.dto.ResponseDTO;
import ch.cern.todo.dto.UserDTO;
import ch.cern.todo.session.InMemorySessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    public AuthenticationManager manager;

    @Autowired
    public InMemorySessionRegistry sessionRegistry;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
        );

        final String sessionId = sessionRegistry.registerSession(user.getUserName());

        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
    }
}
