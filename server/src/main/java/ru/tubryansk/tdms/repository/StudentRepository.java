package ru.tubryansk.tdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tubryansk.tdms.entity.Student;
import ru.tubryansk.tdms.entity.User;
import ru.tubryansk.tdms.exception.NotFoundException;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    default Student findByIdThrow(Integer id) {
        return this.findById(id).orElseThrow(() -> new NotFoundException(Student.class, id));
    }

    Optional<Student> findByUser(User user);
}
