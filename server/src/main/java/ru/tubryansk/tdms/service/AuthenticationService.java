package ru.tubryansk.tdms.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.tubryansk.tdms.entity.User;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class AuthenticationService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean authenticated() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated() && (authentication.getPrincipal() instanceof User);
    }

    public void logout() {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }

    public void login(String username, String password) {
        var context = SecurityContextHolder.createEmptyContext();
        var token = new UsernamePasswordAuthenticationToken(username, password);
        var authenticated = authenticationManager.authenticate(token);
        context.setAuthentication(authenticated);
        request.getSession(true).setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);
    }
}
