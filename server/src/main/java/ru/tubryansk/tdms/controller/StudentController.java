package ru.tubryansk.tdms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tubryansk.tdms.dto.StudentDTO;
import ru.tubryansk.tdms.service.StudentService;

@RestController
@RequestMapping("/api/v1/student/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/current")
    public StudentDTO getCurrentStudent() {
        return studentService.getCallerStudent().map(StudentDTO::from).orElse(null);
    }
}
