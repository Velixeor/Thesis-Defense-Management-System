package ru.tubryansk.tdms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tubryansk.tdms.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    Boolean existsByLoginOrNumberPhoneOrMail(String login, String numberPhone, String mail);

}
