package ru.tubryansk.tdms.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tubryansk.tdms.entity.User;
import ru.tubryansk.tdms.repository.UserRepository;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;

    public User getCallerPrincipal() {
        if(!authenticated()) {
            return null;
        }

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean authenticated() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void logout() {
        HttpSession session = httpServletRequest.getSession(true);
        // if(session != null) {
        //     session.invalidate();
        // }
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, null);
    }
}
