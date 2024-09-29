package ru.tubryansk.tdms.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tubryansk.tdms.entity.DiplomaTopic;
import ru.tubryansk.tdms.exception.NotFoundException;
import ru.tubryansk.tdms.repository.DiplomaTopicRepository;
import ru.tubryansk.tdms.dto.DiplomaTopicDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiplomaTopicService {
    @Autowired
    private DiplomaTopicRepository diplomaTopicRepository;

    public List<DiplomaTopicDTO> getAll() {
        return diplomaTopicRepository.findAll()
                .stream()
                .map(DiplomaTopicDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public DiplomaTopicDTO getById(Integer id) {
        return DiplomaTopicDTO.fromEntity(diplomaTopicRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(DiplomaTopic.class, id)));
    }
}
