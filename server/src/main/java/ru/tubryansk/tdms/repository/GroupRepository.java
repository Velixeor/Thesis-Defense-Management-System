package ru.tubryansk.tdms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tubryansk.tdms.entity.Group;

import java.util.List;



@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> getAllGroups();

}
