package ru.tubryansk.tdms.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.entity.User;
import ru.tubryansk.tdms.exception.user.UserCreationException;
import ru.tubryansk.tdms.repository.RoleRepository;
import ru.tubryansk.tdms.repository.UserRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;


@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    public User getCallerPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void logout() {
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, null);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user;
        userDTO = new UserDTO(
                userDTO.authenticated(),
                userDTO.login(),
                "{noop}" + userDTO.password(),
                userDTO.fullName(),
                userDTO.email(),
                userDTO.phone(),
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                userDTO.authorities()
        );
        if (!userRepository.existsUserByLoginOrNumberPhoneOrMail(userDTO.login(), userDTO.phone(), userDTO.email())) {
            user = UserDTO.toEntity(userDTO);
            user.setRoles(new ArrayList<>(List.of(roleRepository.findRoleById(3))));
            User resultUser = userRepository.save(user);
            return UserDTO.from(resultUser, true);
        } else {
            throw new UserCreationException(userDTO);
        }
    }

    public void loginUser(String login, String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login, password);
            Authentication authenticated = authenticationConfiguration.getAuthenticationManager().authenticate(authentication);
            context.setAuthentication(authenticated);
            SecurityContextHolder.setContext(context);
            HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
            securityContextRepository.saveContext(context, request, response);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка аутентификации: " + e.getMessage(), e);
        }
    }

}
