package ru.tubryansk.tdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tubryansk.tdms.entity.DiplomaTopic;

@Repository
public interface DiplomaTopicRepository extends JpaRepository<DiplomaTopic, Integer> {
}