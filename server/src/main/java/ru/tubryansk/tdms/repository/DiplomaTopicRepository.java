package ru.tubryansk.tdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tubryansk.tdms.entity.DiplomaTopic;
import ru.tubryansk.tdms.exception.NotFoundException;

@Repository
public interface DiplomaTopicRepository extends JpaRepository<DiplomaTopic, Integer> {
    default DiplomaTopic findByIdThrow(Integer id) {
        return this.findById(id).orElseThrow(() -> new NotFoundException(DiplomaTopic.class, id));
    }
}