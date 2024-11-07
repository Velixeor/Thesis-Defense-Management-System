package ru.tubryansk.tdms.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.entity.User;
import ru.tubryansk.tdms.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsersByGroupID(int groupID) {
        List<User> users = userRepository.getAllUsersByGroupId(groupID);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setLogin(user.getLogin());
            userDTO.setPassword(user.getPassword());
            userDTO.setFullName(user.getFullName());
            userDTO.setMail(user.getMail());
            userDTO.setNumberPhone(user.getNumberPhone());
            userDTO.setCreateAt(user.getCreateAt());
            userDTO.setUpdateAt(user.getUpdateAt());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}
