package ru.tubryansk.tdms.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tubryansk.tdms.dto.GroupDTO;
import ru.tubryansk.tdms.entity.Group;
import ru.tubryansk.tdms.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class GroupService {
    @Autowired
    private  GroupRepository groupRepository;

    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.getAllGroups();
        List<GroupDTO> groupDTOs = new ArrayList<>();
        for (Group group : groups) {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setId(group.getId());
            groupDTO.setName(group.getName());
            groupDTO.setPrincipalUser(group.getPrincipalUser());
            groupDTOs.add(groupDTO);
        }
        return groupDTOs;
    }
}
