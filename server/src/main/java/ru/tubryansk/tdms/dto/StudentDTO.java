package ru.tubryansk.tdms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tubryansk.tdms.entity.Student;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Boolean form;
    private Integer protectionOrder;
    private String magistracy;
    private Boolean digitalFormatPresent;
    private Integer markComment;
    private Integer markPractice;
    private String predefenceComment;
    private String normalControl;
    private Integer antiPlagiarism;
    private String note;
    private Boolean recordBookReturned;
    private String work;
    private UserDTO user;
    private String diplomaTopic;
    private UserDTO mentorUser;
    private GroupDTO group;

    public static StudentDTO from(Student student) {
        return new StudentDTO(
                student.getForm(),
                student.getProtectionOrder(),
                student.getMagistracy(),
                student.getDigitalFormatPresent(),
                student.getMarkComment(),
                student.getMarkPractice(),
                student.getPredefenceComment(),
                student.getNormalControl(),
                student.getAntiPlagiarism(),
                student.getNote(),
                student.getRecordBookReturned(),
                student.getWork(),
                UserDTO.from(student.getUser(), true),
                student.getDiplomaTopic().getName(),
                UserDTO.from(student.getMentorUser(), true),
                GroupDTO.from(student.getGroup())
        );
    }
}
