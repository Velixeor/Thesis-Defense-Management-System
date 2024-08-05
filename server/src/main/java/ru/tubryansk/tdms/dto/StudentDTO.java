package ru.tubryansk.tdms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Integer id;
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
    private Integer userId;
    private Integer diplomaTopicId;
    private Integer mentorUserId;
    private Integer groupId;
}
