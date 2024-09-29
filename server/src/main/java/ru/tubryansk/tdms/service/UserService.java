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
import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.entity.User;
import ru.tubryansk.tdms.exception.user.UserCreationException;
import ru.tubryansk.tdms.repository.RoleRepository;
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
    @Autowired
    private RoleRepository roleRepository;

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
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void logout() {
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, null);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user;
        if (!userRepository.existsByLoginOrNumberPhoneOrMail(userDTO.login(), userDTO.numberPhone(), userDTO.mail())) {
            user = UserDTO.toEntity(userDTO, roleRepository::findAllById);
            User resultUser = userRepository.save(user);
            UserDTO resultUserDTO = UserDTO.from(resultUser,true);
            log.info("User created successfully: {}", userDTO);
            return resultUserDTO;
        } else {
            throw new UserCreationException(userDTO);
        }
    }

}
