package ru.tubryansk.tdms.exception.user;


import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.exception.BusinessException;



public class UserCreationException extends BusinessException {
    public UserCreationException(UserDTO userDTO) {
        super("Failed to create user " + userDTO);
    }
}
