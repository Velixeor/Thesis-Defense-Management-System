package ru.tubryansk.tdms.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diploma_topic", schema = "vkr")
public class DiplomaTopic {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Макс из-за SEQUENCE прога лежит, если хочешь, можешь менять я не понял что не нравиться при сборке
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
}
