package ru.tubryansk.tdms.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tubryansk.tdms.entity.User;
import ru.tubryansk.tdms.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<User> getCallerUser() {
        if(authenticationService.authenticated()) {
            return Optional.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }
        return Optional.empty();
    }
}
