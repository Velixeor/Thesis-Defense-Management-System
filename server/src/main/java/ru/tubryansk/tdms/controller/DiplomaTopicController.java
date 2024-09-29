package ru.tubryansk.tdms.controller;


import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tubryansk.tdms.dto.DiplomaTopicDTO;
import ru.tubryansk.tdms.service.DiplomaTopicService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/diploma-topic/")
@Validated
public class DiplomaTopicController {
    @Autowired
    private DiplomaTopicService diplomaTopicService;

    @GetMapping("/get-all")
    public List<DiplomaTopicDTO> getAll() {
        return diplomaTopicService.getAll();
    }

    @GetMapping("/get-by-id/{id:[\\-+]?\\d+}")
    public DiplomaTopicDTO getById(@PathVariable @PositiveOrZero Integer id) {
        return diplomaTopicService.getById(id);
    }
}
