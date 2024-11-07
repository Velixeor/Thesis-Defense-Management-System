package ru.tubryansk.tdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tubryansk.tdms.entity.User;

import java.util.List;
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getAllUsersByGroupId(Integer gtoupId);
}
