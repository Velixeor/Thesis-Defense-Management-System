package ru.tubryansk.tdms.dto;


import lombok.Builder;
import ru.tubryansk.tdms.entity.Student;


@Builder
public record StudentDTO(
        Integer id,
        Boolean form,
        Integer protectionOrder,
        String magistracy,
        Boolean digitalFormatPresent,
        Integer markComment,
        Integer markPractice,
        String predefenceComment,
        String normalControl,
        Integer antiPlagiarism,
        String note,
        Boolean recordBookReturned,
        String work,
        Integer userId,
        Integer diplomaTopicId,
        Integer mentorUserId,
        Integer groupId) {


    public static StudentDTO fromEntity(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .form(student.getForm())
                .protectionOrder(student.getProtectionOrder())
                .magistracy(student.getMagistracy())
                .digitalFormatPresent(student.getDigitalFormatPresent())
                .markComment(student.getMarkComment())
                .markPractice(student.getMarkPractice())
                .predefenceComment(student.getPredefenceComment())
                .normalControl(student.getNormalControl())
                .antiPlagiarism(student.getAntiPlagiarism())
                .note(student.getNote())
                .recordBookReturned(student.getRecordBookReturned())
                .work(student.getWork())
                .userId(student.getUser().getId())
                .diplomaTopicId(student.getDiplomaTopic().getId())
                .mentorUserId(student.getMentorUser().getId())
                .groupId(student.getGroup().getId())
                .build();
    }
}
