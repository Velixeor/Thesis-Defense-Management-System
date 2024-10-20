package ru.tubryansk.tdms.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tubryansk.tdms.entity.DiplomaTopic;
import ru.tubryansk.tdms.entity.Student;
import ru.tubryansk.tdms.exception.AccessDeniedException;
import ru.tubryansk.tdms.repository.DiplomaTopicRepository;
import ru.tubryansk.tdms.repository.StudentRepository;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DiplomaTopicRepository diplomaTopicRepository;
    @Autowired
    private Optional<Student> student;
    @Autowired
    private UserService userService;

    /** @param studentToDiplomaTopic Map of @{@link Student} id and @{@link DiplomaTopic} id */
    public void changeDiplomaTopic(Map<Integer, Integer> studentToDiplomaTopic) {
        studentToDiplomaTopic.forEach(this::changeDiplomaTopic);
    }

    public void changeDiplomaTopic(Integer studentId, Integer diplomaTopicId) {
        Student student = studentRepository.findByIdThrow(studentId);
        DiplomaTopic diplomaTopic = diplomaTopicRepository.findByIdThrow(diplomaTopicId);
        student.setDiplomaTopic(diplomaTopic);
    }

    public void changeCallerDiplomaTopic(Integer diplomaTopicId) {
        DiplomaTopic diplomaTopic = diplomaTopicRepository.findByIdThrow(diplomaTopicId);
        student.ifPresentOrElse(s -> s.setDiplomaTopic(diplomaTopic), () -> {throw new AccessDeniedException();});
    }

    public Optional<Student> getCallerStudent() {
        return studentRepository.findByUser(userService.getCallerUser().orElse(null));
    }
}
