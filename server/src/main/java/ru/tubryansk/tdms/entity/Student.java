package ru.tubryansk.tdms.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;


@Data
@Entity
@Table(name = "student", schema = "vkr")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "form")
    private Boolean form;
    @Column(name = "protection_order" , nullable = false)
    private Integer protectionOrder;
    @Column(name = "magistracy" )
    private String magistracy;
    @Column(name = "digital_format_present")
    private Boolean digitalFormatPresent;
    @Column(name = "mark_comment")
    private Integer markComment;
    @Column(name = "mark_practice")
    private Integer markPractice;
    @Column(name = "predefence_comment")
    private String predefenceComment;
    @Column(name = "normal_control")
    private String normalControl;
    @Column(name = "anti_plagiarism")
    private Integer antiPlagiarism;
    @Column(name = "note")
    private String note;
    @Column(name = "record_book_returned")
    private Boolean recordBookReturned;
    @Column(name = "work")
    private String  work;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "diploma_topic_id", nullable = false)
    private  DiplomaTopic diplomaTopic;
    @ManyToOne
    @JoinColumn(name = "mentor_user_id", nullable = false)
    private User mentorUser;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}
