package ru.tubryansk.tdms.exception.user;


import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.exception.BusinessException;



public class UserUpdateException extends BusinessException {
    public UserUpdateException(UserDTO userDTO) {
        super("Failed to update user " + userDTO);
    }
}
